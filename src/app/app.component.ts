import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl} from '@angular/forms';
import {HttpService} from './http.service';
import {ToastrService} from 'ngx-toastr';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Uitbating evenementencomplex';

  startDate: any;
  endDate: any;
  visitors = '2000';
  price = '15.99';
  capacity = '500';
  halls = new FormControl();
  templateDate = new FormControl(new Date());
  hallList = ['hall1', 'hall2', 'hall3', 'hall4', 'hall5', 'hall6'];

  constructor(private http: HttpService, private toastr: ToastrService) {
      this.onStartDateChange(this.templateDate.value);
      this.onEndDateChange(this.templateDate.value);
      this.onAlertEvent.subscribe(
        (alert) => {
          switch (alert.type) {
            case 'info':
              this.toastr.info(alert.message, alert.title);
              break;
            case 'error':
              this.toastr.error(alert.message, alert.title);
              break;
            case 'success':
              this.toastr.success(alert.message, alert.title);
              break;
            case 'warning':
              this.toastr.warning(alert.message, alert.title);
              break;
          }
          console.log(alert.message);
        }
      );
  }

  @Output() onAlertEvent = new EventEmitter<any>();

  // =========================== View Logic ===========================
  onStartDateChange(value) {
    const d = new Date(value);
    const date = [('0' + d.getDate()).slice(-2), ('0' + (d.getMonth() + 1)).slice(-2), d.getFullYear()].join('/');
    this.startDate = date;
    console.log(this.startDate);
  }
  onEndDateChange(value) {
    const d = new Date(value);
    const date = [('0' + d.getDate()).slice(-2), ('0' + (d.getMonth() + 1)).slice(-2), d.getFullYear()].join('/');
    this.endDate = date;
    console.log(this.endDate);
  }
  onHallsSelectionChange(e) {
    console.log(this.halls.value);
  }

  // =========================== Event Reservation ===========================
  getReservationAvailability() {
    console.log(this.startDate, this.endDate, this.capacity, this.halls.value);
    this.http.getReservationAvailability(this.startDate, this.endDate, this.capacity, this.halls.value)
      .then(result => {
        this.onAlertEvent.emit({
          title: 'Success',
          message: 'Reservation is available!',
          type: 'success'
        });
      })
      .catch(err => {
        this.onAlertEvent.emit({
          title: 'Error',
          message: 'Reservation is not available',
          type: 'error'
        });
      });
  }
  createReservation() {
    console.log(this.startDate, this.endDate, this.capacity, this.halls.value, this.visitors, this.price);
    this.http.createReservation(this.startDate, this.endDate, this.capacity, this.halls.value, this.visitors, this.price)
      .then(result => {
        this.onAlertEvent.emit({
          title: 'Success',
          message: 'Reservation created!',
          type: 'success'
        });
      })
      .catch(err => {
        this.onAlertEvent.emit({
          title: 'Error',
          message: 'Reservation is not available',
          type: 'error'
        });
      });
  }

  // =========================== Event Management ===========================
  getEventInformation(eventId) {}
  endEvent(eventId) {}

  // =========================== Tracking ===========================
  getProgress() {}
  updateProgress() {}

  // =========================== Parking ===========================
  createParkingTicket() {}
  validateParkingTicket(ticketId) {}
  exitParking(ticketId) {}

  // =========================== Ticket ===========================
  getTicketAvailability() {}
  buyTicket() {}
  validateTicket() {}

  // =========================== Badge ===========================
  rechargeBadge() {}

  // =========================== Food and Drinks ===========================
  createOrder() {}

  // =========================== Cloakroom ===========================
  addCloakroomItem() {}
  removeCloakroomItem() {}

  // =========================== Multimedia ===========================
  updateInformationBoards() {}

  // =========================== Security ===========================
  triggerEmergency() {}

}
