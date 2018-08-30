package com.test.customelockdemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // to work with auto_increment column
    @Column(name = "customerId")
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private Date createDateTime;
    private Date updateDateTime;
}
