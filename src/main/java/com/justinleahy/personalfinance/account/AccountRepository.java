package com.justinleahy.personalfinance.account;

import com.justinleahy.personalfinance.account.Account;
import com.justinleahy.personalfinance.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Override
    @NonNull
    Optional<Account> findById(@NonNull Long id);

    List<Account> findByAccountType(@NonNull int accountType);

    List<Account> findByUser(@NonNull User user);
}
