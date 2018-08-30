package com.test.customelockdemo.repository;

import com.test.customelockdemo.CustomLockNameEnum;
import com.test.customelockdemo.model.CustomLockEntity;
import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomLockRepository extends JpaRepository<CustomLockEntity, Integer>, CustomLockRepositoryCustom {
}
