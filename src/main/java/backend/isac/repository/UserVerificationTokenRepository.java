package backend.isac.repository;

import backend.isac.model.User;
import backend.isac.model.UserVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserVerificationTokenRepository extends JpaRepository<UserVerificationToken, Long> {

    @Query("SELECT s FROM UserVerificationToken s WHERE s.user = :user")
    Optional<UserVerificationToken> findByUser(User user);

    @Query("SELECT s FROM UserVerificationToken s WHERE s.token = :token")
    Optional<UserVerificationToken> findByToken(@Param("token") String token);

}
