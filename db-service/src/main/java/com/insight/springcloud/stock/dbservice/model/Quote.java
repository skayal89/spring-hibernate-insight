package com.insight.springcloud.stock.dbservice.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Quote", catalog = "stock")
@Data
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "quoteId")
    Integer quoteId;

    @Column(name = "userName")
    String userName;

    @Column(name = "value")
    String value;
}
