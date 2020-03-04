package com.platform.delivery.service;

import java.util.List;

import com.platform.delivery.domain.Delivery;
import com.platform.delivery.domain.Order;

public interface DeliveryService {

	Delivery placeOrder(Order order) throws Exception;
	String updateOrderStatus(int id, String status);
	List<Delivery> getOrdersByPage(int page, int limit);
	
}
