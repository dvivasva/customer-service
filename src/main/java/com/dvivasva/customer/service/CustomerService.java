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

	  public Flux<CustomerDto> getAllCustomer(){
	    return customerRepository.findAll().map(CustomerUtil::entityToDto);
	  }

	  public Mono<CustomerDto> getCustomerById(String id){
	    return  customerRepository.findById(id).map(CustomerUtil::entityToDto);
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

	public Mono<CustomerDto> updateCustomer(Mono<CustomerDto> customerDtoMono,String id){
		return customerRepository.findById(id)
				.flatMap(p->customerDtoMono.map(CustomerUtil::dtoToEntity)
						.doOnNext(e->e.setId(id)))
				.flatMap(customerRepository::save)
				.map(CustomerUtil::entityToDto);

	}

	public Mono<Void> deleteCustomer(String id){
		return customerRepository.deleteById(id);
	}


}
