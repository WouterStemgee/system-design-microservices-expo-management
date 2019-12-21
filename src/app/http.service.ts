import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams, HttpResponse} from '@angular/common/http';
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
  getEventInformation(eventId) {
    const params = new HttpParams().set('eventId', eventId);
    return new Promise((resolve, reject) => {
      this.http.get(environment.apiGatewayUri + '/event-management/information', {params}).subscribe(
        result => {
          resolve(result);
        },
        (error: HttpErrorResponse) => {
          reject(error);
        }
      );
    });
  }
  endEvent(eventId) {
    return new Promise((resolve, reject) => {
      this.http.post(environment.apiGatewayUri + '/event-management/end/' + eventId, {})
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

  // Parking
  createParkingTicket() {}
  validateParkingTicket(ticketId) {}
  exitParking(ticketId) {}

  // Tracking
  getProgress() {}
  updateProgress() {}

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
