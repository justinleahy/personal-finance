package com.justinleahy.personalfinance.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TransactionsCategoriesRepository extends CrudRepository<TransactionsCategories, Long> {
    @Override
    @NonNull
    Optional<TransactionsCategories> findById(@NonNull Long id);

    List<Transaction> findByTransaction(Transaction transaction);
}
