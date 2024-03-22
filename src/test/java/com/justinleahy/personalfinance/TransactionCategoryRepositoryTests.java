package com.justinleahy.personalfinance;

import com.justinleahy.personalfinance.transaction.TransactionCategory;
import com.justinleahy.personalfinance.transaction.TransactionCategoryRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionCategoryRepositoryTests {

    private static final Logger log = LoggerFactory.getLogger(TransactionCategoryRepositoryTests.class);

    @Autowired
    private TransactionCategoryRepository transactionCategoryRepository;

    @Test
    void testFindById() {
        TransactionCategory transactionCategory = new TransactionCategory("Subscription");
        transactionCategoryRepository.save(transactionCategory);

        TransactionCategory foundTransactionCategory = transactionCategoryRepository.findById(transactionCategory.getId()).orElse(null);

        assertNotNull(foundTransactionCategory);
        assertEquals(transactionCategory.getName(), foundTransactionCategory.getName());

        log.info("Transaction Category was found: {}", foundTransactionCategory);
    }

    @Test
    void testFindByName() {
        TransactionCategory transactionCategory = new TransactionCategory("Groceries");
        transactionCategoryRepository.save(transactionCategory);

        List<TransactionCategory> foundTransactionCategories = transactionCategoryRepository.findByName(transactionCategory.getName());

        assertNotNull(foundTransactionCategories);
        assertEquals(1, foundTransactionCategories.size());
        assertEquals(transactionCategory.getName(), foundTransactionCategories.get(0).getName());

        log.info("Transaction Categories were found: {}", foundTransactionCategories);
    }

    @Test
    void testDelete() {
        TransactionCategory transactionCategory = new TransactionCategory("Car Loan");
        transactionCategoryRepository.save(transactionCategory);

        transactionCategoryRepository.delete(transactionCategory);

        TransactionCategory deletedTransactionCategory = transactionCategoryRepository.findById(transactionCategory.getId()).orElse(null);

        assertNull(deletedTransactionCategory);

        log.info("Transaction Category was deleted: {}", transactionCategory);
    }

    @Test
    void testUpdate() {
        TransactionCategory transactionCategory = new TransactionCategory("Student Loan");
        transactionCategoryRepository.save(transactionCategory);

        transactionCategory.setName("Updated Student Loan");
        transactionCategoryRepository.save(transactionCategory);

        TransactionCategory updatedTransactionCategory = transactionCategoryRepository.findById(transactionCategory.getId()).orElse(null);

        assertNotNull(updatedTransactionCategory);
        assertEquals("Updated Student Loan", updatedTransactionCategory.getName());

        log.info("Transaction Category was updated: {}", updatedTransactionCategory);
    }
}
