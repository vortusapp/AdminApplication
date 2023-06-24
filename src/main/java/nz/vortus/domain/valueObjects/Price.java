package nz.vortus.domain.valueObjects;

import java.math.BigDecimal;
import java.util.Objects;

// Price.java
public record Price(BigDecimal unitPrice, int accountCode, String taxType){
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getAccountCode() {
        return accountCode;
    }

    public String getTaxType() {
        return taxType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Price other = (Price) obj;
        return Objects.equals(unitPrice, other.unitPrice) &&
                Objects.equals(accountCode, other.accountCode) &&
                Objects.equals(taxType, other.taxType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice, accountCode, taxType);
    }
}
