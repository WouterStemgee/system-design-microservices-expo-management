package be.ugent.sysdev2.multimedia.persistence;

import be.ugent.sysdev2.multimedia.domain.MessageBoard;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageBoardRepository extends CassandraRepository<MessageBoard,Integer> {


}
