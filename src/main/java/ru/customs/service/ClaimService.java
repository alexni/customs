package ru.customs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.customs.dao.ClaimsDao;
import ru.customs.dto.*;
import ru.customs.entity.*;
import ru.customs.repository.ClaimRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ClaimService {
    Logger log = Logger.getLogger(ClaimService.class.toString());

    @Autowired
    ClaimRepository repository;

    @Autowired
    ClaimsDao dao;

    @Autowired
    DeclarantService declarantService;

    @Autowired
    UserService userService;



    public ClaimsResponse search(ClaimState state, int limit, int offset) {
        List<ClaimEntity> result = dao.getClaimsByState(state, limit, offset);
        List<ClaimResponse> ret = response(result);
        Long count = dao.getCount(state);
        List<UserResponse> users = new ArrayList<>();
        for (ClaimEntity entity: result){
            UserEntity managerEntity = userService.get(entity.getManagerId());
            if (managerEntity!=null) {
                users.add(new UserResponse(managerEntity));
            }
        }
        return new ClaimsResponse(ret, count, limit, offset, users);
    }

    public ClaimsResponse search(int limit, int offset) {

        List<ClaimEntity> result = dao.getClaims(limit, offset);
        List<UserResponse> users = new ArrayList<>();
        for (ClaimEntity entity: result){
            UserEntity managerEntity = userService.get(entity.getManagerId());
            if (managerEntity!=null) {
                users.add(new UserResponse(managerEntity));
            }
        }
        List<ClaimResponse> ret = response(result);
        Long count = dao.getCount();
        return new ClaimsResponse(ret, count, limit, offset, users);
    }

    public ClaimsResponse search(int managerId, int limit, int offset, ClaimState state) {
        List<ClaimEntity> result = null;
        if (managerId != -1 && state != ClaimState.None){
            System.out.println("Request by manager and state "+managerId+" "+state);
            result = dao.getClaimsByManagerAndState(state, managerId, limit, offset);
        } else if (managerId != -1) {
            System.out.println("Request by manager "+managerId);
            result = dao.getClaimsByManager(managerId, limit, offset);
        } else if (state!=ClaimState.None){
            System.out.println("Request by  state "+state);
            result = dao.getClaimsByState(state, limit, offset);
        } else {
            System.out.println("Request simple "+managerId +" "+state);
            result = dao.getClaims(limit, offset);
        }
        if (result == null) {
            System.out.println("Claims not found manager: "+managerId+" stae: "+state+" limit: "+limit + " offset: "+offset );
            result = new ArrayList<>();
        }
        List<UserResponse> users = new ArrayList<>();
        for (ClaimEntity entity: result){
            UserEntity managerEntity = userService.get(entity.getManagerId());
            if (managerEntity!=null) {
                users.add(new UserResponse(managerEntity));
            }
        }
        List<ClaimResponse> ret = response(result);
        Long count = dao.getCount();
        return new ClaimsResponse(ret, count, limit, offset, users);
    }

    private List<ClaimResponse> response(List<ClaimEntity> result) {
        List<ClaimResponse> ret = new ArrayList<>();
        for (ClaimEntity entity: result){
            DeclarantEntity declarant = declarantService.getByPhone(entity.getPhone());
            List<Long> managers = new ArrayList<>();
            if (entity.getManagerId()!=0) {
                managers.add(entity.getManagerId());
            }
            ret.add(new ClaimResponse(entity, managers, new DeclarantResponse(declarant)));
        }
        return ret;
    }

    public ClaimEntity create(NewClaimRequest claimRequest, String token) {

        Long count = declarantService.getCountByPhone(claimRequest.getPhone());
        if (count == 0){
            declarantService.save(new DeclarantEntity(claimRequest, token));
        }
        return repository.save(new ClaimEntity(claimRequest));

    }

    public ClaimResponse getById(Long id) {
        ClaimEntity entity = repository.findById(id).get();
        DeclarantEntity declarant = declarantService.getByPhone(entity.getPhone());
        List<Long> managers = new ArrayList<>();
        if (entity.getManagerId()!=0) {
            managers.add(entity.getManagerId());
        }
        repository.dropNewMessage(id);
        return new ClaimResponse(entity, managers, new DeclarantResponse(declarant));
    }

    public void setState(Long id, ClaimState state, Long managerId) {
        repository.updateState(id, state, managerId);
    }

    public Long getByDeclarant(Long id){
        return dao.getByDeclarant(id);
    }

    public void setNewMessage(Long claimId) {
        repository.setNewMessage(claimId);
    }
}
