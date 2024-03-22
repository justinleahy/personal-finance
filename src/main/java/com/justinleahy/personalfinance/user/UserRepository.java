package com.justinleahy.personalfinance.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUserName(String userName);

    @Override
    @NonNull
    Optional<User> findById(@NonNull Long id);
}
