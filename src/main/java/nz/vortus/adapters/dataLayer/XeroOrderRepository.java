package nz.vortus.adapters.dataLayer;

import nz.vortus.domain.entities.Order;

import java.util.List;

public interface XeroOrderRepository {
    List<Order> getOrders();

    void updateOrder(Order order);
}
