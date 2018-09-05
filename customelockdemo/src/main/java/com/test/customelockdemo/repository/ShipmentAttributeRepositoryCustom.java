package com.test.customelockdemo.repository;

import com.test.customelockdemo.model.ShipmentAttribute;

import java.util.List;

public interface ShipmentAttributeRepositoryCustom {
    @Deprecated // better alternative findValueByShipmentIdAndName is available in ShipmentAttributeRepository
    String fetchAttributeValue(String shipmentId, String attributeName);
    void update(String shipmentId, String attributeName, String attributeValue);
}
