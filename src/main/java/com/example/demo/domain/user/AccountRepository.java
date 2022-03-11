package com.example.demo.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // @EntityGraph는 쿼리가 수행이 될때 Lazy 조회가 아니고 Eager 조회로 Authorities 정보를 같이 가져오게 됨
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByEmailAndStateIsLessThan(String email, int limit);

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByIdAndStateIsLessThan(Long id, int limit);

    Optional<Account> findByEmailAndStateIsLessThan(String email, int limit);
}
