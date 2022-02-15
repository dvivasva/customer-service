package com.dvivasva.customer.controller;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.model.Customer;
import com.dvivasva.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
	
private final CustomerService customerService;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Customer>>>getAllCustomer() {
		Flux<Customer> list=this.customerService.getAllCustomer();
		return  Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(list));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable String id){
		var customer=this.customerService.getCustomerById(id);
		return customer.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<CustomerDto> create(@RequestBody Mono<CustomerDto> customerDtoMono){
		return this.customerService.createCustomer(customerDtoMono);
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Customer>> updateCustomerById(@PathVariable String id, @RequestBody Customer customer){

		return this.customerService.updateCustomer(id,customer)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteCustomerById(@PathVariable String id){
		return this.customerService.deleteCustomer(id)
				.map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
