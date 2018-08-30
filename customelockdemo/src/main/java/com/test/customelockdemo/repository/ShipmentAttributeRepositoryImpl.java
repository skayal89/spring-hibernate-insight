package com.test.customelockdemo.repository;

import com.test.customelockdemo.model.ShipmentAttribute;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class ShipmentAttributeRepositoryImpl implements ShipmentAttributeRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public String fetchAttributeValue(String shipmentId, String attributeName) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<ShipmentAttribute> criteriaQuery=criteriaBuilder.createQuery(ShipmentAttribute.class);
        Root<ShipmentAttribute> root=criteriaQuery.from(ShipmentAttribute.class);
        criteriaQuery.select(root).where(
                criteriaBuilder.equal(root.get("shipmentId"),shipmentId),
                criteriaBuilder.equal(root.get("name"),attributeName)
        );
        ShipmentAttribute attribute = entityManager.createQuery(criteriaQuery).getSingleResult();
        if(attribute!=null){
            return attribute.getValue();
        }
        return null;
    }

    @Override
    public void update(String shipmentId, String attributeName, String attributeValue) {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaUpdate<ShipmentAttribute> criteriaUpdate=criteriaBuilder.createCriteriaUpdate(ShipmentAttribute.class);
        Root<ShipmentAttribute> root=criteriaUpdate.from(ShipmentAttribute.class);
        criteriaUpdate.set("value", attributeValue).where(
                criteriaBuilder.equal(root.get("name"),attributeName),
                criteriaBuilder.equal(root.get("shipmentId"),shipmentId)
        );
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

}
