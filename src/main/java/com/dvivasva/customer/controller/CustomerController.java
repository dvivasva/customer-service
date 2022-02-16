package com.dvivasva.customer.controller;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
	
private final CustomerService customerService;
	
	@GetMapping
	public Flux<CustomerDto>getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@GetMapping("/{id}")
	public Mono<CustomerDto> getCustomerById(@PathVariable String id){
		return customerService.getCustomerById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<CustomerDto> create(@RequestBody Mono<CustomerDto> customerDtoMono){
		return customerService.createCustomer(customerDtoMono);
	}

	@PutMapping("/{id}")
	public Mono<CustomerDto> updateCustomerById( @RequestBody Mono<CustomerDto> customerDtoMono,@PathVariable String id){
		return customerService.updateCustomer(customerDtoMono,id);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteCustomerById(@PathVariable String id){
		return customerService.deleteCustomer(id);
	}
}
