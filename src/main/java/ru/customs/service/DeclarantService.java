package ru.customs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.customs.dao.DeclarantDao;
import ru.customs.dto.DeclarantStateRequest;
import ru.customs.dto.DeclarantsResponse;
import ru.customs.entity.DeclarantEntity;
import ru.customs.repository.DeclarantRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class DeclarantService {

    Logger log = Logger.getLogger(DeclarantService.class.toString());


    @Autowired
    DeclarantRepository repository;

    @Autowired
    DeclarantDao dao;



    public DeclarantsResponse getDeclarants(int limit, int offset) {
        List<DeclarantEntity> ret = dao.getDeclarants(limit, offset);
        Long total = dao.getCount();
        return new DeclarantsResponse(ret, total);
    }

    public DeclarantEntity save(DeclarantEntity entity){
        DeclarantEntity stored = null;
        try {
            stored = repository.findByfirebaseId(entity.getFirebase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (stored == null) {
            repository.save(entity);
        } else {
            repository.update(entity.getFirebase(), entity.getBirthday(), entity.getName(), entity.getPassportDate(),
                    entity.getPassportNumber(), entity.getPassportSeries(), entity.getPatronymic(), entity.getPhone(),
                    entity.getSurname());
        }
        return repository.findByfirebaseId(entity.getFirebase());
    }

    public DeclarantEntity getByPhone(String phone){
        return repository.findByPhone(phone);
    }

    public DeclarantEntity getFirebaseId(String firebase){
        return repository.findByfirebaseId(firebase);
    }

    public DeclarantEntity getById(Long id) {return repository.findById(id).get();}

    public Long getCountByPhone(String phone) {
        return dao.getCountByPhone(phone);
    }

    public void setState(Long id, DeclarantStateRequest state) {
        repository.setState(id, state.getState());
    }
}
