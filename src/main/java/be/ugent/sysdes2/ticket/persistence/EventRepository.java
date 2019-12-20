package be.ugent.sysdes2.ticket.persistence;

import be.ugent.sysdes2.ticket.domain.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    @Modifying
    @Transactional
    @Query("update Event set available_tickets = ?1 where event_id = ?2")
    void updateAvailableTickets(int availableTickets, int eventId);
}
