package com.test.customelockdemo.service;

import com.test.customelockdemo.model.ShipmentAttribute;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShipmentAttributeService {
    void add(ShipmentAttribute shipmentAttribute);
    List<ShipmentAttribute> getAllAttributes(String shipmentId);
    ShipmentAttribute get(String shipmentId, String attributeName);
    String findValue(String shipmentId, String attributeName);
    void updateValue(String shipmentId, String attributeName, String newValue);
}
