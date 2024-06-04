package com.cyphersoft.osahaneat.service;

import com.cyphersoft.osahaneat.entity.*;
import com.cyphersoft.osahaneat.entity.keys.KeyOrderItem;
import com.cyphersoft.osahaneat.payload.request.OrderRequest;
import com.cyphersoft.osahaneat.repository.OrderItemRepository;
import com.cyphersoft.osahaneat.repository.OrderRepository;
import com.cyphersoft.osahaneat.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class OrderService implements OrderServiceImp {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;
    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try{

            Users users = new Users();
            users.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getRestaurantId());

            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);
            orderRepository.save(orders);

            List<OrderItem> items = new ArrayList<>();

            for(int idFood: orderRequest.getFoodIds()){

                Food food = new Food();
                food.setId(idFood);

                OrderItem orderItem = new OrderItem();
                KeyOrderItem keyOrderItem =new KeyOrderItem(orders.getId(), idFood);
                orderItem.setKeyOrderItem(keyOrderItem);
                items.add(orderItem);
            }
            orderItemRepository.saveAll(items);
            return true;
        }
        catch(Exception e){
            System.out.println("Error Insert Order "+e.getMessage());
            return false;
        }
    }
}
