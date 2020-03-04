package com.platform.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.platform.delivery.domain.Delivery;
import com.platform.delivery.domain.Order;
import com.platform.delivery.repository.DeliveryRepository;

@Transactional(isolation = Isolation.SERIALIZABLE)
@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	@Value("${google.api.key}")
	private String apiKey;
	
	@Override
	public Delivery placeOrder(Order order) throws Exception {
		Delivery delivery = new Delivery();
		delivery.setStatus("UNASSIGNED");
		GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		LatLng origin = new LatLng(Double.valueOf(order.getOrigin()[0]), Double.valueOf(order.getOrigin()[1]));
		LatLng destination = new LatLng(Double.valueOf(order.getDestination()[0]), Double.valueOf(order.getDestination()[1]));
		try {
			DistanceMatrix t = req.destinations(destination)
					.origins(origin).mode(TravelMode.WALKING).await();
			delivery.setDistance(t.rows[0].elements[0].distance.inMeters);
		} catch (Exception e) {
			throw e;
		}
		deliveryRepository.saveAndFlush(delivery);
		return delivery;
	}

	@Override
	public String updateOrderStatus(int id, String status) {
		deliveryRepository.updateOrderById(id, status);
		return "SUCCESS";
	}

	@Override
	public List<Delivery> getOrdersByPage(int page, int limit) {
		Pageable sortedByPriceDesc = PageRequest.of(page, limit);
		return deliveryRepository.findAll(sortedByPriceDesc).getContent();
	}

}
