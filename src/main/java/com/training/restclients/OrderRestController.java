/**
 * 
 */
package com.training.restclients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.feign.client.OrderClient;
import com.training.model.Orders;

/**
 * @author admin
 *
 */
@RestController
@RequestMapping("/api")
public class OrderRestController {

	@Autowired
	OrderClient orderClient;
	
	@RequestMapping("/order")
	public ResponseEntity<List<Orders>> getAllOrders() {
		
		List<Orders> orders = orderClient.findAll();
		if(orders != null && !orders.isEmpty()) {
			return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/order",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orders> getAllOrders(@RequestBody Orders orders) {
		
		Orders ordersResp = orderClient.postNewOrder(orders);
		
		if(ordersResp != null) {
			return new ResponseEntity<Orders>(ordersResp, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
