package com.test.customelockdemo.resource;

import com.test.customelockdemo.model.ShipmentAttribute;
import com.test.customelockdemo.service.ShipmentAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class ShipmentAttributeResource {

    @Autowired
    ShipmentAttributeService attributeService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void add(@RequestBody ShipmentAttribute shipmentAttribute){
        attributeService.add(shipmentAttribute);
    }

    @GetMapping("/find-all/{shipmentId}")
    List<ShipmentAttribute> getAll(@PathVariable("shipmentId") String shipmentId){
        return attributeService.getAllAttributes(shipmentId);
    }

    @PostMapping(path = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    ShipmentAttribute fetch(@RequestBody ShipmentAttribute attribute){
        return attributeService.get(attribute.getShipmentId(), attribute.getName());
    }

    // http://localhost:8301/attribute/find?shipmentId=10098&name=customer_promise_date
    @GetMapping(path = "/find")
    String fetch(@RequestParam String shipmentId, @RequestParam("name") String attributeName){
        return attributeService.findValue(shipmentId, attributeName);
    }

    @PostMapping(path = "/update")
    void update(@RequestBody ShipmentAttribute attribute){
        attributeService.updateValue(attribute.getShipmentId(), attribute.getName(), attribute.getValue());
    }
}
