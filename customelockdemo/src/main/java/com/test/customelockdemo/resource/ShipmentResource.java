package com.test.customelockdemo.resource;

import com.test.customelockdemo.model.Shipment;
import com.test.customelockdemo.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentResource {

    @Autowired
    ShipmentService shipmentService;

    @GetMapping("/all")
    List<Shipment> getAllShipments(){
        return shipmentService.findAll();
    }

    @GetMapping("/id/{sid}")
    Shipment get(@PathVariable("sid") int id){
        return shipmentService.findById(id);
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void add(@RequestBody Shipment shipment){
        shipmentService.add(shipment);
    }
}
