package ru.customs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.customs.dto.ClaimResponse;
import ru.customs.entity.DeclarantEntity;
import ru.customs.entity.FriendlyMessage;
import ru.customs.entity.MessageEntity;
import ru.customs.entity.UserEntity;
import ru.customs.repository.MesageRepository;

import java.util.logging.Logger;

@Service
public class MessageService {
    Logger log = Logger.getLogger(MessageService.class.toString());

    @Autowired
    MesageRepository repository;

    @Autowired
    ClaimService claimService;

    @Autowired
    DeclarantService declarantService;

    public Iterable<MessageEntity> getAllMessagesByClaim(Long claimId) {
        return repository.findAllByCalimId(claimId);
    }

    public void saveToLastClaim(MessageEntity message, Long claimId) {
        System.out.println("Save message "+message.getId() + " text: "+message.getText()+" for claim: "+claimId);
        Long realClaimId = claimService.getByDeclarant(claimId);
        claimService.setNewMessage(realClaimId);
        message.setClaimId(realClaimId);
        repository.save(message);
    }

    public void send(MessageEntity message, UserEntity manager) {
        System.out.println("Send  message "+message.getId() + " text: "+message.getText()+" for claim: "+message.getClaimId());
        ClaimResponse claim = claimService.getById(message.getClaimId());
        DeclarantEntity declarant = declarantService.getById(claim.getDeclarant().getId());
        new Firebase().update(new FriendlyMessage(message.getText(), manager.getName()), declarant.getFirebase());

    }

    public void saveToClaim(MessageEntity message) {
        System.out.println("Save to claim message "+message.getId() + " text: "+message.getText()+" for claim: "+message.getClaimId());
        claimService.setNewMessage(message.getClaimId());
        repository.save(message);
    }

    public Iterable<MessageEntity> getAllMessagesByDeclarant(Long id) {
        return repository.findAllByDeclarant(id);
        }
}
