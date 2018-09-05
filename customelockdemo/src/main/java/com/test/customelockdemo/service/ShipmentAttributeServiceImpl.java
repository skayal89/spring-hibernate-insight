package com.test.customelockdemo.service;

import com.test.customelockdemo.model.ShipmentAttribute;
import com.test.customelockdemo.repository.ShipmentAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentAttributeServiceImpl implements ShipmentAttributeService {

    @Autowired
    ShipmentAttributeRepository shipmentAttributeRepository;

    @Override
    public void add(ShipmentAttribute shipmentAttribute) {
        shipmentAttributeRepository.save(shipmentAttribute);
    }

    @Override
    public List<ShipmentAttribute> getAllAttributes(String shipmentId) {
        return shipmentAttributeRepository.findAllByShipmentId(shipmentId);
    }

    @Override
    public ShipmentAttribute get(String shipmentId, String attributeName) {
//        ShipmentAttribute attribute = shipmentAttributeRepository.findOneByShipmentIdAndName(shipmentId,attributeName);
//        if(attribute!=null){
//            return attribute.getValue();
//        }
//        return null;
        return shipmentAttributeRepository.findOneByShipmentIdAndName(shipmentId, attributeName);
    }

    @Override
    public String findValue(String shipmentId, String attributeName) {
        return shipmentAttributeRepository.findValueByShipmentIdAndName(shipmentId, attributeName).orElse(null);
    }

    @Override
    public void updateValue(String shipmentId, String attributeName, String newValue) {
        ShipmentAttribute attribute=shipmentAttributeRepository.findOneByShipmentIdAndName(shipmentId, attributeName);
        if(attribute!=null){
            attribute.setValue(newValue);
        }
        shipmentAttributeRepository.save(attribute);
    }
}
