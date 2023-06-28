/**
 * 
 */
package com.car_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car_portal.model.Car;

/**
 * author: chittebabu
 */

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	// find cars by make
	List<Car> findCarsByCarMake(String carMake);
	
	// find car by model
	Car findCarByCarModel(String carModel);
}
