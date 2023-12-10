package org.myproject.cash.cockpit.handler.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDAO extends AbstractDAO {

    @Column(name = "date", nullable = false)
    private LocalDate transactionDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_info_id")
    private TransactionInfoDAO transactionInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type_id")
    private TransactionTypeDAO transactionType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id")
    private ConsumerDAO consumer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private SenderDAO sender;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinTable(name = "transaction_tag",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagDAO> tags;

    @Column(name = "total")
    private double total;

    @Column(name = "user_id")
    private UUID userId;
}
