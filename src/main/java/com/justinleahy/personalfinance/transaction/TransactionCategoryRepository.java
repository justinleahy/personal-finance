package com.justinleahy.personalfinance.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long> {

    @Override
    @NonNull
    Optional<TransactionCategory> findById(@NonNull Long id);

    List<TransactionCategory> findByName(String name);
}
