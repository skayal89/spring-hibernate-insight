package com.test.customelockdemo.repository;

import com.test.customelockdemo.CustomLockNameEnum;
import com.test.customelockdemo.model.CustomLockEntity;
import org.hibernate.LockMode;

import java.util.List;

/*
 To provide additional methods along with the default JpaRepository,
 we need to create a separate interface and provide the implementation

 https://stackoverflow.com/questions/11880924/how-to-add-custom-method-to-spring-data-jpa
 https://dzone.com/articles/accessing-the-entitymanager-from-spring-data-jpa
 https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito

 It is mandatory for our interface to follow the naming convention of ${Original Repository name}Custom
 */
public interface CustomLockRepositoryCustom {
    boolean lockEntities(CustomLockEntity lockEntity, LockMode lockMode, int queryTimeOutInSeconds);
}
