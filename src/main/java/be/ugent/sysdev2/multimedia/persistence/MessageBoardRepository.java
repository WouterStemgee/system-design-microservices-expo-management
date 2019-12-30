package be.ugent.sysdev2.multimedia.persistence;

import be.ugent.sysdev2.multimedia.domain.MessageBoard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageBoardRepository extends CrudRepository<MessageBoard,Integer> {
    public MessageBoard findById(int id);

    @Query("Select m from MessageBoard m")
    public Iterable<MessageBoard> findAllMessageboards();
}
