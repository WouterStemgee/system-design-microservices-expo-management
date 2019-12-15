package be.ugent.sysdes2.ticket.persistence;

import be.ugent.sysdes2.ticket.domain.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}
