package org.csu.manage.persistence;

import org.csu.manage.domain.Account;
import org.csu.manage.domain.Item;
import org.csu.manage.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getOrderList();
    List<Item> getLineItemList();
    void updateOrder(Order order);
    Order getOrder(String orderId);
    void deleteOrder(Order order);
}
