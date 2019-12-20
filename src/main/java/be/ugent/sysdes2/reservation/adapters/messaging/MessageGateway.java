package be.ugent.sysdes2.reservation.adapters.messaging;

import be.ugent.sysdes2.reservation.domain.Reservation;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = Channels.RESERVATION_CREATED)
    public void emitReservationCreated(Reservation reservation);

}
