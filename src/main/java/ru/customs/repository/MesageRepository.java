package ru.customs.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.customs.entity.MessageEntity;

public interface MesageRepository extends CrudRepository<MessageEntity, Long> {
    @Query(value = "Select m from MessageEntity m where m.claimId = :claimId")
    Iterable<MessageEntity> findAllByCalimId(Long claimId);

    @Query(value = "Select m from MessageEntity m where m.declarantId = :id order by timestamp asc")
    Iterable<MessageEntity> findAllByDeclarant(Long id);
}
