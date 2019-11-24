package ru.customs.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.customs.entity.FCMTokenEntity;
import ru.customs.entity.MessageEntity;

import javax.transaction.Transactional;

public interface FCMTokenRepository extends CrudRepository<FCMTokenEntity, Long> {


    @Query(value = "Select t from FCMTokenEntity t where t.userToken = :userToken")
    FCMTokenEntity findByUser(String userToken);

    @Modifying
    @Transactional
    @Query(value = "Update FCMTokenEntity set FCMToken = :FCMToken where userToken = :userToken")
    void update(String userToken, String FCMToken);
}
