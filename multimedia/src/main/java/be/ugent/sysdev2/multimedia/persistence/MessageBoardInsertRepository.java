package be.ugent.sysdev2.multimedia.persistence;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MessageBoardInsertRepository {
    @PersistenceContext
    private EntityManager entitymanager;

    @Transactional
    public void insert(String message) {
        entitymanager.createNativeQuery("INSERT INTO message_board (message) VALUES (?)")
                .setParameter(1, message)
                .executeUpdate();
    }

}
