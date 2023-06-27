/**
 * 
 */
package com.car_portal.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author: chittebabu
 */

@Data
@AllArgsConstructor
public class DeleteResponse {
	// define entities' properties
	private String entityName;
	private Long entityId;
	
	@Override
	public String toString() {
		return entityName + "/" + entityId + " deleted successfully!";
	}
}
