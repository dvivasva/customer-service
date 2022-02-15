package com.dvivasva.customer.service;


import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.model.Customer;
import com.dvivasva.customer.repository.CustomerRepository;
import com.dvivasva.customer.utils.CustomerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	 private  final CustomerRepository customerRepository;

	  public Flux<Customer> getAllCustomer(){
	    return customerRepository.findAll();
	  }
	  public Mono<Customer> getCustomerById(String id){
	    return  customerRepository.findById(id);
	  }

	  public Mono<CustomerDto> createCustomer(Mono<CustomerDto> entityToDto){

		  Mono<CustomerDto> result=entityToDto.map(
				  p -> {
					  if (p.getTypeCustomer().equals("Empresa"))
						  p.setProfile("PYME");
					  else
						  p.setProfile("VID");
					  return p;
				  });

		  return result.map(CustomerUtil::dtoToEntity)
				  .flatMap(customerRepository::save)
				  .map(CustomerUtil::entityToDto);

	  }
	  public Mono<Customer> updateCustomer(String id, Customer customer){
	    return customerRepository.findById(id)
	            .flatMap(bean -> {
	              bean.setName(customer.getName());
				  bean.setLastname(customer.getLastname());
				  bean.setDni(customer.getDni());
	              bean.setTypeCustomer(customer.getTypeCustomer());
				  bean.setProfile(customer.getProfile());
				  return customerRepository.save(bean);
	            });
	  }
	  public Mono<Customer> deleteCustomer(String id){
	    return customerRepository.findById(id)
	            .flatMap(existsCustomerRepository -> customerRepository.delete(existsCustomerRepository)
	                    .then(Mono.just(existsCustomerRepository)));
	  }


}
