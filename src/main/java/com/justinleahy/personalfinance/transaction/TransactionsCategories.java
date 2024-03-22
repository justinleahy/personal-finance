package com.justinleahy.personalfinance.transaction;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TransactionsCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "transaction_category_id")
    private TransactionCategory transactionCategory;

    /*

    Consider createdDateTime / lastModifiedDateTime, not sure whether this would be needed because if a category
    is removed, we'd be deleting the entry from the database. This database would contain entries of just
    id, transaction_id, and transaction_category_id

     */

    protected TransactionsCategories() {}

    public TransactionsCategories(Transaction transaction, TransactionCategory transactionCategory) {
        this.transaction = transaction;
        this.transactionCategory = transactionCategory;
    }
}
