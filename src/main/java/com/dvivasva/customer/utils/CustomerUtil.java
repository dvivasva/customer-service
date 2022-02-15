package com.dvivasva.customer.utils;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.model.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerUtil {

    public static CustomerDto entityToDto(Customer customer){
        var customerDto=new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }
    public static Customer dtoToEntity(CustomerDto customerDto){
        var entity=new Customer();
        BeanUtils.copyProperties(customerDto,entity);
        return entity;
    }

}
