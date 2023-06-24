package nz.vortus.domain.valueObjects;

import nz.vortus.domain.valueObjects.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    private Price price;
    private int accountCode;
    private BigDecimal unitPrice;
    private String taxType;

    @BeforeEach
    void setUp() {
        accountCode = 200;
        unitPrice = new BigDecimal(1.0);
        taxType = "OUTPUT";
        price = new Price(unitPrice, accountCode, taxType);

    }

    @Test
    void createNewPrice(){
        assertTrue(price != null);
    }

    @Test
    void createNewPriceWithUnitPrice_GetUnitPrice(){
        assertEquals(unitPrice, price.getUnitPrice());
    }

    @Test
    void createNewPriceWithAccountCode_getAccountCode(){
        assertEquals(accountCode, price.getAccountCode());
    }

    @Test
    void createNewPriceWithTaxType_getTaxType(){
        assertEquals(taxType, price.getTaxType());
    }

    @Test
    void createTwoPriceWithSameValue_PricesAreEqual(){
        Price price2 = new Price(unitPrice, accountCode, taxType);
        assertEquals(price, price2);
        assertTrue(price.equals(price2));
    }

}