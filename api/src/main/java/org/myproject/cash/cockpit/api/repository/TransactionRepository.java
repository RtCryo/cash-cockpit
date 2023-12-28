package org.myproject.cash.cockpit.api.repository;

import org.myproject.cash.cockpit.api.repository.model.TagDAO;
import org.myproject.cash.cockpit.api.repository.model.TransactionDAO;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends ListCrudRepository<TransactionDAO, UUID> {
    List<TransactionDAO> findAllByTagsIn(Iterable<TagDAO> tagDAO);

    List<TransactionDAO> findAllByUserDAOAndTransactionDateBetweenOrderByTransactionDate(UserDAO userDAO, LocalDate start, LocalDate end);

    @Query(nativeQuery = true,
            value = "select t.*\n" +
                    "from transaction t\n" +
                    "         join (\n" +
                    "    select t.*\n" +
                    "    from transaction t\n" +
                    "             join transaction_tag tt on t.id = tt.transaction_id\n" +
                    "             join tag t2 on tt.tag_id = t2.id\n" +
                    "    where t2.id in :transactionTag\n" +
                    "      and t.date between :transactionDate and :transactionDate2" +
                    "    group by t.id\n" +
                    "    having count(*) = :tagSize\n" +
                    ") ids on ids.id = t.id where t.user_id = :userId order by t.date")
    List<TransactionDAO> findByDateBetweenAndTagsOrderByDate(LocalDate transactionDate, LocalDate transactionDate2, Iterable<UUID> transactionTag, int tagSize, UUID userId);

    List<TransactionDAO> findAllByUserDAO(UserDAO userDAO);
}
