package com.test.customelockdemo.repository;

import com.test.customelockdemo.model.ShipmentAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentAttributeRepository extends JpaRepository<ShipmentAttribute, Integer>, ShipmentAttributeRepositoryCustom {
    List<ShipmentAttribute> findAllByShipmentId(String shipmentId);
    ShipmentAttribute findOneByShipmentIdAndName(String shipmentId, String name);

    @Query("SELECT a.value FROM ShipmentAttribute a where a.shipmentId = :shipmentId AND a.name = :name")
    Optional<String> findValueByShipmentIdAndName(@Param("shipmentId") String shipmentId, @Param("name") String name);
}
