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
  halls = new FormControl([]);
  templateDate = new FormControl(new Date());
  hallList = ['hall1', 'hall2', 'hall3', 'hall4', 'hall5', 'hall6'];
  eventId = '5dfe00ea2eada541a1044540';

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
  error(err) {
    if (err.error) {
      if (err.error.message) {
        if (err.error.message.match(/"\d{3} .*",/)) {
          return err.error.message.match(/"\d{3} .*",/).toString().substr(4).slice(0, -2);
        }
        return err.error.message;
      }
      return err.error;
    }
    return err;
  }

  // =========================== Event Reservation ===========================
  getReservationAvailability() {
    if (this.startDate <= this.endDate) {
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
            message: this.error(err),
            type: 'error'
          });
        });
    }
  }
  createReservation() {
    if (this.startDate <= this.endDate) {
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
            message: this.error(err),
            type: 'error'
          });
        });
    }
  }

  // =========================== Event Management ===========================
  getEventInformation() {
    this.http.getEventInformation(this.eventId)
      .then(result => {
        this.onAlertEvent.emit({
          title: 'Success',
          message: JSON.stringify(result),
          type: 'success'
        });
      })
      .catch(err => {
        this.onAlertEvent.emit({
          title: 'Error',
          message: this.error(err),
          type: 'error'
        });
      });
  }
  endEvent() {
    this.http.endEvent(this.eventId)
      .then(result => {
        this.onAlertEvent.emit({
          title: 'Success',
          message: result,
          type: 'success'
        });
      })
      .catch(err => {
        this.onAlertEvent.emit({
          title: 'Error',
          message: this.error(err),
          type: 'error'
        });
      });
  }
  // =========================== Parking ===========================
  createParkingTicket() {}
  validateParkingTicket(ticketId) {}
  exitParking(ticketId) {}

  // =========================== Tracking ===========================
  getProgress() {}
  updateProgress() {}

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
