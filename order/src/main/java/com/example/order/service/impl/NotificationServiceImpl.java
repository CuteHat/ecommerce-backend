package com.example.order.service.impl;

import com.example.order.model.NotificationDto;
import com.example.order.model.NotificationType;
import com.example.order.model.OrderItem;
import com.example.order.productapi.ProductDto;
import com.example.order.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    /**
     * Creates seller notifications based on the order items and product data.
     *
     * @param orderItems  the list of order items
     * @param productDtos the list of product data
     * @return a list of seller notifications
     */
    @Override
    public List<NotificationDto> createSellerNotifications(List<OrderItem> orderItems, List<ProductDto> productDtos) {
        List<NotificationDto> sellerNotifications = new ArrayList<>();

        // create product to quantity map
        Map<Long, Integer> productToQuantityMap = orderItems
                .stream()
                .collect(Collectors.toMap(OrderItem::getProductId, OrderItem::getQuantity));

        // create seller to Products map
        Map<Long, List<ProductDto>> sellerToProductsMap = productDtos
                .stream()
                .collect(Collectors.groupingBy(ProductDto::getSellerId));

        // iterate over seller to products map
        for (Map.Entry<Long, List<ProductDto>> entry : sellerToProductsMap.entrySet()) {
            Long sellerId = entry.getKey();
            List<ProductDto> products = entry.getValue();

            // create email body
            StringBuilder emailBody = new StringBuilder("You have sold the following products: ");
            for (ProductDto product : products) {
                Integer quantity = productToQuantityMap.get(product.getId());
                String sellerEmailFormat = "%d - (%d with id %d),";
                emailBody.append(String.format(sellerEmailFormat, quantity, product.getName(), product.getId()));
            }

            // send email
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setType(NotificationType.EMAIL);
            notificationDto.setMessage(emailBody.toString());
            notificationDto.setRecipient(products.get(0).getSellerEmail());
        }
        return sellerNotifications;
    }

    @Override
    public NotificationDto createClientNotifications(Long orderId, BigDecimal totalPrice, String clientPhoneNumber) {
        String text = String.format("Your order %d has been placed successfully. Total price: %f", orderId, totalPrice);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setType(NotificationType.SMS);
        notificationDto.setMessage(text);
        notificationDto.setRecipient(clientPhoneNumber);
        return notificationDto;
    }

}
