/**
 * 
 */
package com.car_portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * author: chittebabu
 */

@Entity
@Table(name = "cars")
@Data
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id", nullable = false, columnDefinition = "INT AUTO_INCREMENT")
	private Long carId;
	
	@Column(name = "car_make", nullable = false)
	private String carMake;
	
	@Column(name = "car_model", nullable = false)
	private String carModel;
	
	@Column(name = "car_price", nullable = false)
	private float carPrice;
	
	@Column(name = "year_built", nullable = false)
	private Long yearBuilt;
}
