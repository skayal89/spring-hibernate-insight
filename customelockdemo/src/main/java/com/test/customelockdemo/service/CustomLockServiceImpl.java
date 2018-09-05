package com.test.customelockdemo.service;

import com.test.customelockdemo.CustomLockNameEnum;
import com.test.customelockdemo.model.CustomLockEntity;
import com.test.customelockdemo.repository.CustomLockRepository;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomLockServiceImpl implements CustomLockService {

    @Autowired
    CustomLockRepository customLockRepository;

    private int queryTimeOut = 100;

    @Override
    public void createLockEntry(CustomLockEntity lockEntity) {
        try {
            // custom_lock table definition has UNIQUE KEY `UC_Shipment` (`entity_name`,`entity_id`)
            customLockRepository.save(lockEntity);
        } catch (Exception e) {
            System.err.println("Unable to add CustomLockEntity to DB as :"+ e);
        }
        System.out.println("Created Lock Entry for Type : " + lockEntity);
    }

    @Override
    public void acquireLock(CustomLockEntity lockEntity) {

        acquireLock(lockEntity, LockMode.PESSIMISTIC_WRITE, queryTimeOut);
    }

    @Override
    public List<CustomLockEntity> findAllByIdAndName(CustomLockNameEnum entityName, String entityId) {
        return customLockRepository.findAllByEntityIdAndEntityName(entityId, entityName);
    }

    /**
     * @param lockEntity            entityName: Custom Lock Entity Type (ENUM) on which we want to acquire a lock. entityId: Primary key in custom_lock table , denoting the row in db on which we want to acquire the lock
     * @param lockMode              The type of lock we want to acquire on the entity, Exclusive(PESSIMISTIC) or shared
     * @param queryTimeOutInSeconds Query TimeOut in seconds (default value taken from Config Bucket)
     *
     * NOTE : LockTimeOut functionality depends on underlying database and cannot be overridden by Hibernate in case of MySql DB
     */


    @Override
    public void acquireLock(CustomLockEntity lockEntity, LockMode lockMode, int queryTimeOutInSeconds) {
        try {
            System.out.println("Thread with ID : " + Thread.currentThread().getId() + " Attempting to acquire lock on : " + lockEntity);
            long lockStartTime = System.currentTimeMillis();
            boolean isLockSuccessful = customLockRepository.lockEntities(lockEntity, lockMode, queryTimeOutInSeconds);
            long lockEndTime = System.currentTimeMillis();
            if (isLockSuccessful) {
                System.out.println("Lock Acquired by - Thread ID:" + Thread.currentThread().getId() + " for Entity name :" + lockEntity + " In Time :" + (lockEndTime - lockStartTime) + " ms");
            } else {
                System.out.println("Thread ID :" + Thread.currentThread().getId() + " Unable to acquire lock on " + lockEntity + "because : ");

            }
        } catch (Exception e) {
            System.out.println("Thread ID :" + Thread.currentThread().getId() + " Unable to acquire lock on " + lockEntity + "because : " + e);

        }
    }
}
