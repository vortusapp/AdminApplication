package nz.vortus.adapters.dataLayer;

import nz.vortus.domain.entities.Order;
import com.shopify.model.ShopifyOrder;

import java.util.List;

public interface ShopifyOrderRepository {
    List<ShopifyOrder> getOrders();

    void updateOrder(Order order);
}
