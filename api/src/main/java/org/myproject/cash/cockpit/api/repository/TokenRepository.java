package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.TokenDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenDAO, UUID> {

    @Query(value = """
            select t from TokenDAO t inner join UserDAO u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<TokenDAO> findAllValidTokenByUser(UUID id);

    Optional<TokenDAO> findByToken(String token);

}
