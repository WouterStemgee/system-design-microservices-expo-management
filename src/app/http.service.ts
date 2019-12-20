import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  // Event Reservation
  getReservationAvailability() {}
  createReservation() {}

  // Event Management
  getEventInformation() {}
  endEvent() {}

  // Tracking
  getProgress() {}
  updateProgress() {}

  // Parking
  createParkingTicket() {}
  validateParkingTicket() {}
  exitParking() {}

  // Ticket
  getTicketAvailability() {}
  buyTicket() {}
  validateTicket() {}

  // Badge
  rechargeBadge() {}

  // Food and Drinks
  createOrder() {}

  // Cloakroom
  addCloakroomItem() {}
  removeCloakroomItem() {}

  // Multimedia
  updateInformationBoards() {}

  // Security
  triggerEmergency() {}
}
