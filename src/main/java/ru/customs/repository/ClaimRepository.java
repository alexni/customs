package ru.customs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.customs.entity.ClaimEntity;
import ru.customs.entity.ClaimState;

import javax.transaction.Transactional;


public interface ClaimRepository extends JpaRepository<ClaimEntity, Long> {
    @Modifying
    @Transactional
    @Query("update ClaimEntity set state = :state, managerId = :managerId where id = :id")
    void updateState(Long id, ClaimState state, Long managerId);

    @Modifying
    @Transactional
    @Query("update ClaimEntity c set c.haveNewMessages=true where c.id = :id")
    void setNewMessage(Long id);

    @Modifying
    @Transactional
    @Query("update ClaimEntity c set c.haveNewMessages=false where c.id = :id")
    void dropNewMessage(Long id);
}
