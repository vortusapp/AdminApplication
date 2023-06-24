package nz.vortus.adapters.dataLayer;

import nz.vortus.domain.entities.Order;
import com.shopify.ShopifySdk;
import com.shopify.model.ShopifyOrder;

import java.util.List;

public class ShopifyOrderRepositoryImpl implements ShopifyOrderRepository {


    public ShopifyOrderRepositoryImpl(String shopifyAccessToken, String shopifySubdomain) {

    }

    @Override
    public List<ShopifyOrder> getOrders() {
        // Fetch orders from the Shopify API and convert them to domain entities
        return null;
    }

    @Override
    public void updateOrder(Order order) {
        // Update the order in the Shopify API
    }
}
