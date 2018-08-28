package com.test.customelockdemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Shipment", catalog = "stock")
@Data
public class Shipment {
    @Id
    @Column(name = "shipmentId")
    private String id;

    private String pinCode;
    private Double totalPrice;
    private String shipmentType;
    private Date createDateTime;
    private Date updateDateTime;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shippingFromCustomerId")
    public Customer sender;
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "deliveryCustomerId")
    public Customer receiver;
}
