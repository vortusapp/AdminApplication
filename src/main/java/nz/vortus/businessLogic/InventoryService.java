package nz.vortus.businessLogic;



import nz.vortus.adapters.dataLayer.XeroItemRepository;
import nz.vortus.adapters.dataLayer.XeroItemRepositoryImpl;
import com.xero.models.accounting.Item;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

public class InventoryService {

    private XeroItemRepository xeroItemRepository;

    public InventoryService(XeroItemRepository xeroItemRepository) {
        this.xeroItemRepository = xeroItemRepository;
    }
;


    public List<Item> getInventoryItems() {
        return xeroItemRepository.getItems();
    }

    public List<Item> getInventoryItemsSince(OffsetDateTime ifModifiedSince) {
        return xeroItemRepository.getItemsSince(ifModifiedSince);
    }

    public List<Item> getSoldItemsSince(OffsetDateTime ifModifiedSince) {
        return xeroItemRepository.getSoldItemsSince(ifModifiedSince);
    }

    public List<Item> getSoldItems() {
        return xeroItemRepository.getSoldItems();
    }

    public List<Item> getBoughtItemsSince(OffsetDateTime ifModifiedSince) {
        return xeroItemRepository.getBoughtItemsSince(ifModifiedSince);
    }

    public List<Item> getBoughtItems() {
        return xeroItemRepository.getBoughtItems();
    }

    public void updateInventoryItem(Item item) {
        // Add your business logic here before updating the item
        xeroItemRepository.updateItem(item);
    }
}

