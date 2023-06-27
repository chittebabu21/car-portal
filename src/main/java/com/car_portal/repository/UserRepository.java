/**
 * 
 */
package com.car_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car_portal.model.User;

/**
 * author: chittebabu
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// find by email method
	User findByUserEmail(String userEmail);
}
