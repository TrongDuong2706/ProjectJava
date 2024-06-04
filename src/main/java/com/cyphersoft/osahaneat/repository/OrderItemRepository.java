package com.cyphersoft.osahaneat.repository;

import com.cyphersoft.osahaneat.entity.OrderItem;
import com.cyphersoft.osahaneat.entity.keys.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
