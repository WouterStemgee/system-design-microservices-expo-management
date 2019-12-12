package be.ugent.sysdes2.ticket.persistence;

import be.ugent.sysdes2.ticket.domain.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
