package com.platform.delivery.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.platform.delivery.domain.Delivery;
import com.platform.delivery.domain.Order;
import com.platform.delivery.error.ApiError;
import com.platform.delivery.service.DeliveryServiceImpl;

@RestController
public class DeliveryController {
	
	@Autowired
	private DeliveryServiceImpl deliveryService;
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	@ResponseBody
    public Delivery orders(@Valid @RequestBody Order order) {
        try {
			return deliveryService.placeOrder(order);
		} catch (Exception e) {
			throw new ApiError(HttpStatus.NOT_FOUND, "Invalid Origin/Destination");
		}
        
    }
	
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.PATCH)
	@ResponseBody
    public ResponseEntity<String> updateOrder(@PathVariable("id") int id,
    		@RequestBody String status) {
        String orderStatus = ""; 
		try {
			orderStatus = deliveryService.updateOrderStatus(id, status);
		} catch (Exception e) {
			throw new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		}
         return ResponseEntity.status(HttpStatus.OK).body(orderStatus);
    }
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<List<Delivery>> getOrders(@RequestParam int page, @RequestParam int limit) throws ApiError {
        List<Delivery> deliveries = new ArrayList<>();
        try {
        	deliveries = deliveryService.getOrdersByPage(page, limit);
		} catch (NumberFormatException e) {
			throw new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		}
       return ResponseEntity
              .status(HttpStatus.CREATED)                 
              .body(deliveries);
	}
}
