package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.OrderItem;
import com.ecommerce.repository.OrderItemRepository;
@Service
public class OrderItemServiceImpl  implements OrderItemService{

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public OrderItem createOderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
