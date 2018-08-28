package com.insight.springcloud.stock.dbservice.resource;

import com.insight.springcloud.stock.dbservice.model.Quote;
import com.insight.springcloud.stock.dbservice.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class DbServiceController {

    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/user/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {
        return quoteRepository.findByUserName(username)
                .stream()
                .map(Quote::getValue) // .map(quote -> { return getValue() }
                .collect(Collectors.toList());
    }

    @GetMapping("/all-quotes")
    public List<Quote> getUsers(){
        return quoteRepository.findAll();
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Quote q){
        System.out.println(q);
        quoteRepository.save(q);
    }
}
