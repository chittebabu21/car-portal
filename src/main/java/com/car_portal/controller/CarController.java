/**
 * 
 */
package com.car_portal.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_portal.model.Car;
import com.car_portal.response.DeleteResponse;
import com.car_portal.service.CarService;

/**
 * author: chittebabu
 */

@RestController
@RequestMapping("/cars")
public class CarController {
	// get access to service object
	@Autowired
	CarService carService;
	
	// get all cars from database
	@GetMapping
	public List<Car> getAllCars() {
		return carService.getAllCars();
	}
	
	// get car by id
	@GetMapping("/{carId}")
	public Car getCarById(@PathVariable Long carId) {
		return carService.getCarById(carId);
	}
	
	// create new car
	@PostMapping
	public ResponseEntity<Car> addCar(@RequestBody Car car) throws URISyntaxException {
		Car savedCar = carService.saveCar(car);
		return ResponseEntity.created(new URI("/cars/" + savedCar.getCarId())).body(savedCar);
	}
	
	// update car by id
	@PutMapping("/{carId}")
	public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable Long carId) {
		// get car by id
		Car existingCar = carService.getCarById(carId);
		
		// check if car exists in database
		if (existingCar != null) {
			// set properties from request body
			existingCar.setCarMake(car.getCarMake());
			existingCar.setCarModel(car.getCarModel());
			existingCar.setCarPrice(car.getCarPrice());
			existingCar.setYearBuilt(car.getYearBuilt());
			
			// save existing car
			Car updatedCar = carService.saveCar(existingCar);
			
			// return response entity
			return ResponseEntity.ok(updatedCar);
		} else {
			// error handling
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	// delete car by id
	@DeleteMapping("/{carId}")
	public ResponseEntity<DeleteResponse> deleteCarById(@PathVariable Long carId) {
		carService.deleteCarById(carId);
		DeleteResponse response = new DeleteResponse("Car", carId);
		return ResponseEntity.ok(response);
	}
}
