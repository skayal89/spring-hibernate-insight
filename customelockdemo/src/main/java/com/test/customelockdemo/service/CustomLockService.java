package com.test.customelockdemo.service;

import com.test.customelockdemo.CustomLockNameEnum;
import com.test.customelockdemo.model.CustomLockEntity;
import org.hibernate.LockMode;

public interface CustomLockService {

    void createLockEntry(CustomLockEntity lockEntity);

    void acquireLock(CustomLockEntity lockEntity, LockMode lockMode, int queryTimeOutInSeconds);

    void acquireLock(CustomLockEntity lockEntity);

}
