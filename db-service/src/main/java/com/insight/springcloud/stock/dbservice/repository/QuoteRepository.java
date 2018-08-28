package com.insight.springcloud.stock.dbservice.repository;

import com.insight.springcloud.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findByUserName(String userName);
}
