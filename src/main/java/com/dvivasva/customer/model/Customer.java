package com.dvivasva.customer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Customer {
    @Id
    private String id;
    private String name;
    private String lastname;
    private int dni;
    private String typeCustomer;  // personal or enterprise
    private String profile; // VID  PYME
}
