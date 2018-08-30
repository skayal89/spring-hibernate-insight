package com.test.customelockdemo.service;

import com.test.customelockdemo.model.Shipment;
import com.test.customelockdemo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Override
    public void add(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    @Override
    public List<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment findById(int id) {
//        Optional<Shipment> optionalShipment=shipmentRepository.findById(id);
//        if(optionalShipment.isPresent()){
//            return optionalShipment.get();
//        }
//        return null;

//        Same as above

        return shipmentRepository.findById(id).orElse(null);
    }
}
