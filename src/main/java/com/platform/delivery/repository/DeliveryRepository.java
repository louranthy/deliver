package com.platform.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platform.delivery.domain.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
	@Modifying
	@Query("update Delivery d set d.status = ?2 where d.id = ?1")
	void updateOrderById(int id, String status);
}
