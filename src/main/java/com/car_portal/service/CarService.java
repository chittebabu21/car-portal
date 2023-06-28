/**
 * 
 */
package com.car_portal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_portal.model.Car;
import com.car_portal.repository.CarRepository;

/**
 * author: chittebabu
 */

@Service
@Transactional
public class CarService {
	// get access to repository object
	@Autowired
	CarRepository carRepository;
	
	// save car
	public Car saveCar(Car car) {
		return carRepository.save(car);
	}
	
	// get all cars
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}
	
	// get car by id
	public Car getCarById(Long carId) {
		return carRepository.findById(carId).orElseThrow(RuntimeException::new);
	}
	
	// delete car by id
	public void deleteCarById(Long carId) {
		carRepository.deleteById(carId);
	}
}
