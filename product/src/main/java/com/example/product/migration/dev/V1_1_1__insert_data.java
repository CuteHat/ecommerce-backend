package com.example.product.migration.dev;

import com.example.product.persistence.entity.CategoryEntity;
import com.example.product.persistence.entity.ProductEntity;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class V1_1_1__insert_data extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {
        try (Statement statement = context.getConnection().createStatement()) {
            // Insert categories
            List<CategoryEntity> categories = new ArrayList<>();
            List<String> categoryNames = List.of("Category 1", "Category 2");

            for (String categoryName : categoryNames) {
                String sql = String.format("INSERT INTO categories (name) VALUES ('%s') RETURNING id", categoryName);
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    Long categoryId = resultSet.getLong("id");
                    categories.add(new CategoryEntity(categoryId, categoryName));
                }
            }

            // Insert products
            List<ProductEntity> products = List.of(
                    new ProductEntity(null, "Product 1", BigDecimal.valueOf(10), 5, 1L, "seller@example.com", 10, categories.get(0), null, null),
                    new ProductEntity(null, "Product 2", BigDecimal.valueOf(20), 10, 2L, "seller@example.com", 20, categories.get(1), null, null),
                    new ProductEntity(null, "Product 3", BigDecimal.valueOf(30), 15, 1L, "seller@example.com", 30, categories.get(0), null, null)
            );
            products.forEach(product -> {
                String sql = String.format("INSERT INTO products (name, price, quantity, seller_id, seller_email, stock, category_id) VALUES ('%s', %f, %d, %d, '%s', %d, %d)",
                        product.getName(), product.getPrice(), product.getQuantity(), product.getSellerId(), product.getSellerEmail(), product.getStock(), product.getCategory().getId());
                try {
                    statement.execute(sql);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to insert product", e);
                }
            });
        }
    }
}
