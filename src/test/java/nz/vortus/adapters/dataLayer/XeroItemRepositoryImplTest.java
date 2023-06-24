package nz.vortus.adapters.dataLayer;

import nz.vortus.adapters.dataLayer.XeroItemRepositoryImpl;
import com.xero.models.accounting.Item;
import org.junit.jupiter.api.Test;

class XeroItemRepositoryImplTest {

    @Test
    void PrintItemList(){
        XeroItemRepositoryImpl xeroItemRepository = new XeroItemRepositoryImpl();
        Item item = xeroItemRepository.getItems().get(1);
        System.out.println(item.getDescription());
    }

}