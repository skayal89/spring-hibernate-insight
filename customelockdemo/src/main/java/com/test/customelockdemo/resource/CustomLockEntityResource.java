package com.test.customelockdemo.resource;

import com.test.customelockdemo.CustomLockNameEnum;
import com.test.customelockdemo.model.CustomLockEntity;
import com.test.customelockdemo.repository.CustomLockRepository;
import com.test.customelockdemo.service.CustomLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lock")
public class CustomLockEntityResource {

    @Autowired
    CustomLockService customLockService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void add(@RequestBody CustomLockEntity lockEntity){
        System.out.println("Payload: "+lockEntity);
        customLockService.createLockEntry(lockEntity);
    }

    @GetMapping(path = "/find")
    List<CustomLockEntity> findAll(@RequestParam CustomLockNameEnum entityName, @RequestParam String entityId){
        return customLockService.findAllByIdAndName(entityName, entityId);
    }
}
