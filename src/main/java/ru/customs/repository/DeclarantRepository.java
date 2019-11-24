package ru.customs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.customs.dto.DeclarantStateRequest;
import ru.customs.entity.DeclarantEntity;
import ru.customs.entity.DeclarantState;

import javax.transaction.Transactional;

public interface DeclarantRepository extends JpaRepository<DeclarantEntity, Long> {
    @Query("SELECT d from DeclarantEntity d where d.phone = :phone")
    DeclarantEntity findByPhone(String phone);

    @Modifying
    @Transactional
    @Query("UPDATE DeclarantEntity set state = :state where id=:id")
    void setState(Long id, DeclarantState state);

    @Query("SELECT d from DeclarantEntity d where d.firebase = :firebase")
    DeclarantEntity findByfirebaseId(String firebase);

    @Modifying
    @Transactional
    @Query("UPDATE DeclarantEntity set birthday = :birthday, name = :name, passport_date = :passportDate, " +
            "passport_number = :passportNumber, passport_series = :passportSeries, patronymic = :patronymic, " +
            "phone = :phone, surname = :surname where firebase=:firebase")
    void update(String firebase, String birthday, String name, String passportDate, String passportNumber,
                String passportSeries, String patronymic, String phone, String surname);
}
