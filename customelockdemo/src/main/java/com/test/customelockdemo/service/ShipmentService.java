package com.test.customelockdemo.service;

import com.test.customelockdemo.model.Shipment;

import java.util.List;

public interface ShipmentService {
    void add(Shipment shipment);
    List<Shipment> findAll();
    Shipment findById(int id);
}
