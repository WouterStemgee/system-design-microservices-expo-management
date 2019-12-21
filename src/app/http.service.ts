import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  // Event Reservation
  getReservationAvailability(startDate, endDate, capacity, halls) {
    const params = new HttpParams()
      .set('startDate', startDate)
      .set('endDate', endDate)
      .set('capacity', capacity)
      .set('halls', halls);
    return new Promise((resolve, reject) => {
      this.http.get(environment.apiGatewayUri + '/reservation/availability', {params}).subscribe(
        result => {
          resolve(result);
        },
        (error: HttpErrorResponse) => {
          reject(error);
        }
      );
    });
  }
  createReservation(startDate, endDate, capacity, halls, maxVisitors, ticketPrice) {
    return new Promise((resolve, reject) => {
      this.http.post(environment.apiGatewayUri + '/reservation/create',
        {startDate, endDate, capacity, halls, maxVisitors, ticketPrice})
        .subscribe(
        result => {
          resolve(result);
        },
        (error: HttpErrorResponse) => {
          reject(error);
        }
      );
    });
  }

  // Event Management
  getEventInformation(eventId) {}
  endEvent(eventId) {}

  // Tracking
  getProgress() {}
  updateProgress() {}

  // Parking
  createParkingTicket() {}
  validateParkingTicket(ticketId) {}
  exitParking(ticketId) {}

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
