package ru.customs.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.customs.dto.UpdatePasswordRequest;
import ru.customs.entity.UserEntity;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query("Select u from UserEntity u where u.login = :login and u.pass = :password")
    UserEntity findByLoginAndPassword(String login, String password);

    @Query("Select u from UserEntity u where u.login = :login")
    UserEntity findByLogin(String login);

    @Modifying
    @Transactional
    @Query("Update UserEntity set pass= :password where id = :id")
    int updatePassword(Long id, String password);
}
