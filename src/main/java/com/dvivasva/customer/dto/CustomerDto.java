package com.dvivasva.customer.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String id;
    private String name;
    private String lastname;
    private int dni;
    private String typeCustomer;  // personal or enterprise
    private String profile; // VID  PYME
}
