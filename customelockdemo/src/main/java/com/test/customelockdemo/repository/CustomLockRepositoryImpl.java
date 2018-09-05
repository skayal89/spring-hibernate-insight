package com.test.customelockdemo.repository;

import com.test.customelockdemo.CustomLockNameEnum;
import com.test.customelockdemo.model.CustomLockEntity;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CustomLockRepositoryImpl implements CustomLockRepositoryCustom {
// name of this class must be {OriginalRepositoryName}Impl

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean lockEntities(CustomLockEntity lockEntity, LockMode lockMode, int queryTimeOutInSeconds) {
        Query query = entityManager.unwrap(Session.class).createNativeQuery("SELECT * FROM custom_lock WHERE entity_name=:entityName AND entity_id=:entityId")
                .setParameter("entityId", lockEntity.getEntityId())
                .setParameter("entityName", lockEntity.getEntityName())
                .setTimeout(queryTimeOutInSeconds)
                .setLockOptions(new LockOptions(lockMode));
        List result = query.list();
        return (result != null && !result.isEmpty());
    }

}
