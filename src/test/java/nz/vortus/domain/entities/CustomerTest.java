package nz.vortus.domain.entities;

import nz.vortus.domain.entities.Customer;
import nz.vortus.domain.valueObjects.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer customer;

    @BeforeEach
    public void setUp() {
         customer = new Customer();
    }

    @Test
    public void createNewCustomer(){
        assertTrue(customer != null);
    }
    @Test
    public void createNewCustomerWithId(){
        assertNotNull(customer.getId());
    }

    @Test
    public void create2NewCustomersWithDifferentIds(){
        Customer customer2 = new Customer();
        assertNotEquals(customer.getId(), customer2.getId());
    }
    @Test
    public void CustomerIdIsUUid(){
        String id = customer.getId();
        assertDoesNotThrow(() -> {
            UUID.fromString(id);
        });
    }

    @Test
public void setCustomerXeroId_getsCustomerXeroId(){
        String xeroId = "1234";
        customer.setXeroId(xeroId);
        assertEquals(xeroId, customer.getXeroId());
    }

    @Test
    public void setCustomerShopifyId_getsCustomerShopifyId(){
        String shopifyId = "1234";
        customer.setShopifyId(shopifyId);
        assertEquals(shopifyId, customer.getShopifyId());
    }
    @Test
    public void setCustomerCompanyName_getsCustomerCompanyName(){
        String companyName = "Vortus";
        customer.setCompanyName(companyName);
        assertEquals(companyName, customer.getCompanyName());
    }

@Test
    public void setEmail_getEmail(){
        String email = "tim@box.com";
        customer.setEmail(email);
        Assertions.assertEquals(new Email(email), customer.getEmail());
}}

