package nz.vortus.domain.entities;

import nz.vortus.domain.entities.Product;
import nz.vortus.domain.valueObjects.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewProduct(){
        Product product = new Product();
        assertTrue(product != null);
    }

    @Test
    void createNewProductWithId(){
        Product product = new Product();
        assertNotNull(product.getId());
    }

    @Test
    void create2NewProductsWithDifferentIds(){
        Product product = new Product();
        Product product2 = new Product();
        assertNotEquals(product.getId(), product2.getId());
    }

    @Test
    void getIdTwice_returnsSameId(){
        Product product = new Product();
        String id = product.getId();
        assertEquals(id, product.getId());
    }

    @Test
    void setXeroId_getXeroId(){
        Product product = new Product();
        String xeroId = "1234";
        product.setXeroId(xeroId);
        assertEquals(xeroId, product.getXeroId());
    }

    @Test
    void setShopifyId_getShopifyId(){
        Product product = new Product();
        String shopifyId = "1234";
        product.setShopifyId(shopifyId);
        assertEquals(shopifyId, product.getShopifyId());
    }

    @Test
    void setSKU_getSKU(){
        Product product = new Product();
        String sku = "1234";
        product.setSKU(sku);
        assertEquals(sku, product.getSKU());
    }

    @Test
    void setName_GetName(){
        Product product = new Product();
        String name = "1234";
        product.setName(name);
        assertEquals(name, product.getName());
    }

    @Test
    void setDescription_GetDescription(){
        Product product = new Product();
        String description = "123d4";
        product.setDescription(description);
        assertEquals(description, product.getDescription());
    }
    @Test
    void setSalesPrice_GetSalesPrice(){
        Product product = new Product();
        Price salesPrice = new Price(new BigDecimal(1), 1, "OUTPUT");
        product.setSalesPrice(salesPrice);
        assertEquals(salesPrice, product.getSalesPrice());
    }

    @Test
    void setPurchasePrice_GetPurchasePrice(){
        Product product = new Product();
        Price price = new Price(new BigDecimal(1), 1, "OUTPUT");
        product.setPurchasePrice(price);
        assertEquals(price, product.getPurchasePrice());
    }

}