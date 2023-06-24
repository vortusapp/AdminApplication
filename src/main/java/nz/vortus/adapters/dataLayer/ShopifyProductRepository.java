package nz.vortus.adapters.dataLayer;

import nz.vortus.domain.entities.Product;

import java.util.List;

public interface ShopifyProductRepository {
    List<Product> getProducts();
    void updateProduct(Product product);
}

