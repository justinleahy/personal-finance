package com.justinleahy.personalfinance.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends CrudRepository<Vendor, Long> {
    List<Vendor> findByName(String name);

    @Override
    @NonNull
    Optional<Vendor> findById(@NonNull Long id);
}
