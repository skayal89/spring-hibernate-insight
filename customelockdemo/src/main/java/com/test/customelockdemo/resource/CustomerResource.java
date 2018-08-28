package com.test.customelockdemo.resource;

import com.test.customelockdemo.model.Customer;
import com.test.customelockdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerResource {

    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/id/{cid}")
    Customer get(@PathVariable("cid") int id){
        return customerService.findById(id);
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void add(Customer customer){
        customerService.add(customer);
    }
}
