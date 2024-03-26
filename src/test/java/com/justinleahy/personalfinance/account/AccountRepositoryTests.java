package com.justinleahy.personalfinance.account;

import com.justinleahy.personalfinance.user.User;
import com.justinleahy.personalfinance.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
CRUD Test Status

Create : DONE
Read: DONE
Update: DONE
Delete: DONE

Create is done in every test
 */

@SpringBootTest
public class AccountRepositoryTests {

    private static final Logger log = LoggerFactory.getLogger(AccountRepositoryTests.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testFindAccountById() {
        User user = new User("Justin", "Leahy", "justinleahy2", "testPassword");
        userRepository.save(user);

        Account account = new Account("Chase Checking Account", 1, user);
        accountRepository.save(account);

        Account foundAccount = accountRepository.findById(account.getId()).orElse(null);

        assertNotNull(foundAccount);

        log.info("Account was found: {}", foundAccount);
    }

    @Test
    void testFindByAccountType() {
        User user = new User("Justin", "Leahy", "justinleahy1", "testPassword");
        userRepository.save(user);

        Account account1 = new Account("Chase Checking Account", 1, user);
        accountRepository.save(account1);

        Account account2 = new Account("Chase Savings Account", 2, user);
        accountRepository.save(account2);

        List<Account> foundAccounts = accountRepository.findByAccountType(1);

        assertNotNull(foundAccounts);
        assertEquals(1, foundAccounts.size());

        log.info("Accounts with account type 1: {}", foundAccounts);
    }

    @Test
    void testFindByUser() {
        User user = new User("Justin", "Leahy", "justinleahy3", "testPassword");
        userRepository.save(user);

        Account account1 = new Account("Chase Checking Account", 1, user);
        accountRepository.save(account1);

        Account account2 = new Account("Chase Savings Account", 2, user);
        accountRepository.save(account2);

        List<Account> foundAccounts = accountRepository.findByUser(user);

        assertNotNull(foundAccounts);
        assertEquals(2, foundAccounts.size());

        log.info("Accounts for user {}: {}", user.getUserName(), foundAccounts);
    }

    @Test
    void testDeleteAccount() {
        User user = new User("Justin", "Leahy", "justinleahy4", "testPassword");
        userRepository.save(user);

        Account account = new Account("Chase Checking Account", 1, user);
        accountRepository.save(account);

        accountRepository.deleteById(account.getId());

        Account deletedAccount = accountRepository.findById(account.getId()).orElse(null);

        assertNull(deletedAccount);

        log.info("Account was deleted: {}", account);
    }

    @Test
    void testUpdateAccount() {
        User user = new User("Justin", "Leahy", "justinleahy5", "testPassword");
        userRepository.save(user);

        Account account = new Account("Chase Checking Account", 1, user);
        accountRepository.save(account);

        account.setNickname("Chase Secondary Checking Account");
        accountRepository.save(account);

        Account updatedAccount = accountRepository.findById(account.getId()).orElse(null);

        assertNotNull(updatedAccount);
        assertEquals("Chase Secondary Checking Account", updatedAccount.getNickname());

        log.info("Account was updated: {}", updatedAccount);
    }
}
