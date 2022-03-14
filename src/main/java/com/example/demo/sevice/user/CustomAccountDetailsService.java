 package com.example.demo.sevice.user;

 import com.example.demo.domain.user.Account;
 import com.example.demo.domain.user.AccountRepository;
 import com.example.demo.model.StateKind;
 import org.springframework.security.core.authority.SimpleGrantedAuthority;
 import org.springframework.security.core.userdetails.User;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;
 import org.springframework.stereotype.Component;

 import javax.transaction.Transactional;
 import java.util.List;

 @Component("userDetailsService")
 public class CustomAccountDetailsService implements UserDetailsService {

     private final AccountRepository accountRepository;

     public CustomAccountDetailsService(AccountRepository accountRepository) {
         this.accountRepository = accountRepository;
     }

     @Override
     @Transactional
     public UserDetails loadUserByUsername(final String email) {
         return accountRepository.findOneWithAuthoritiesByEmailAndStateIsLessThan(email, StateKind.DELETE.getId())
                 .map(account -> createUser(email, account))
                 .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터 베이스에서 찾을 수 없습니다."));
     }

     private User createUser(String email, Account account) {
         if (account.getState() >= StateKind.DELETE.getId()) {
             throw new IllegalArgumentException(email + " -> 활성화되어 있지 않습니다.");
         }
         List<SimpleGrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
                 .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                 .toList();
         return new User(account.getEmail(), account.getPw(), grantedAuthorities);
     }
 }
