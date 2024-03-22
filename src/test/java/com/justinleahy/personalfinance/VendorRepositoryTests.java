package com.justinleahy.personalfinance;

import com.justinleahy.personalfinance.transaction.Vendor;
import com.justinleahy.personalfinance.transaction.VendorRepository;
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
public class VendorRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(VendorRepositoryTests.class);

    @Autowired
    private VendorRepository vendorRepository;

    @Test
    void testFindByName() {
        Vendor vendor = new Vendor("Tops Friendly Markets");
        vendorRepository.save(vendor);

        List<Vendor> foundVendors = vendorRepository.findByName(vendor.getName());

        assertNotNull(foundVendors);
        assertEquals(1, foundVendors.size());
        assertEquals(vendor.getId(), foundVendors.getFirst().getId());
        assertEquals(vendor.getName(), foundVendors.getFirst().getName());

        log.info("Vendors were found: {}", foundVendors);
    }

    @Test
    void testFindById() {
        Vendor vendor = new Vendor("Walmart");
        vendorRepository.save(vendor);

        Vendor foundVendor = vendorRepository.findById(vendor.getId()).orElse(null);

        assertNotNull(foundVendor);
        assertEquals(vendor.getId(), foundVendor.getId());
        assertEquals(vendor.getName(), foundVendor.getName());

        log.info("Vendor was found: {}", foundVendor);
    }

    @Test
    void testDeleteById() {
        Vendor vendor = new Vendor("Amazon");
        vendorRepository.save(vendor);

        vendorRepository.deleteById(vendor.getId());

        Vendor deletedVendor = vendorRepository.findById(vendor.getId()).orElse(null);
        assertNull(deletedVendor);

        log.info("Vendor was deleted: {}", vendor);
    }

    @Test
    void testUpdate() {
        Vendor vendor = new Vendor("Apple");
        vendorRepository.save(vendor);

        vendor.setName("Google");
        vendorRepository.save(vendor);

        Vendor updatedVendor = vendorRepository.findById(vendor.getId()).orElse(null);

        assertNotNull(updatedVendor);
        assertEquals(vendor.getId(), updatedVendor.getId());
        assertEquals("Google", updatedVendor.getName());

        log.info("Vendor was updated: {}", updatedVendor);
    }
}
