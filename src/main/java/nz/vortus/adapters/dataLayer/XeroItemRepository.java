package nz.vortus.adapters.dataLayer;

import com.xero.models.accounting.Item;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

public interface XeroItemRepository {
    List<Item> getItems();

    List<Item> getItemsSince(OffsetDateTime ifModifiedSince);

    List<Item> getSoldItemsSince(OffsetDateTime ifModifiedSince);

    List<Item> getSoldItems();

    List<Item> getBoughtItemsSince(OffsetDateTime ifModifiedSince);

    List<Item> getBoughtItems();

    void updateItem(Item item);


}
