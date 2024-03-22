package com.justinleahy.personalfinance.transaction;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long> {

    @Override
    @NonNull
    Optional<TransactionCategory> findById(@NonNull Long id);

    List<TransactionCategory> findByName(String name);
}
