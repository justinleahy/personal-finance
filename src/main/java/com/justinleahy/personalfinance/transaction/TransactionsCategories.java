package com.justinleahy.personalfinance.transaction;

import jakarta.persistence.*;

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

    protected TransactionsCategories() {}

    public TransactionsCategories(Transaction transaction, TransactionCategory transactionCategory) {
        this.transaction = transaction;
        this.transactionCategory = transactionCategory;
    }
}
