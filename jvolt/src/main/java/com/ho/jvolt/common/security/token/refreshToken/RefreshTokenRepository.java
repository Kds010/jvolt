package com.ho.jvolt.common.security.token.refreshToken;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository  extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    @Query("select rt from RefreshToken rt " +
            "inner join rt.user ur " +
            "where ur.email = :email")
    Optional<RefreshToken> findByUserEmail(@Param("email") String email);
    // CONSIDER 유지보수성을 생각해서 명시적으로 OneToOne 이지만  "on ur.id = rt.user_id" 를 추가해 관계성을 알려주는것이 좋은가 ???
}
