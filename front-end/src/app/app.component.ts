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
  // tslint:disable-next-line:no-output-on-prefix
  @Output() onAlertEvent = new EventEmitter<any>();

  // Reservation
  startDate: any;
  endDate: any;
  visitors = '2000';
  price = '15.99';
  capacity = '500';
  halls = new FormControl([]);
  templateDate = new FormControl(new Date());
  hallList = ['hall1', 'hall2', 'hall3', 'hall4', 'hall5', 'hall6'];

  // Event Management
  eventId = '5e207c9324d16a4bb0aaa391';

  // Ticket
  name = '';
  numberOfTickets = 0;
  totalPrice = 0;
  ticketId = 0;

  // Badge
  badgeId = 0;
  amount = 0;

  // Food&drinks
  lineItems = [{amount: 5, productId: '1'}, {amount: 3, productId: '2'}];

  // Parking
  parkingTicketId = '1';

  // Multimedia
  message = 'Welkom';

  // Security
  type = 'FIRE';
  severity = '1';
  source = 'Brandweer';

  // Cloakroom
  crBadgeId = 0;
  crItemId = 0;
  crItems = [];

  // Tracking
  trEventId1 = 'test-id';
  trEventId2 = 'test-id';
  trStatus = new FormControl([]);
  trStatusList = ['ENDED', 'CREATED'];
  trEvents = [];


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
      return err.error.toString();
    }
    return err.toString();
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
  createParkingTicket() {
    this.http.createParkingTicket()
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
  validateParkingTicket() {
    this.http.validateParkingTicket(this.parkingTicketId)
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
  exitParking() {
    this.http.exitParking(this.parkingTicketId)
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

  // =========================== Tracking ===========================
  getProgress() {
    this.http.getProgress(this.trEventId1)
      .then(result => {
        this.onAlertEvent.emit({
          title: 'Success',
          message: result,
          type: 'success'
        });
        this.trEvents = result as any[];
      })
      .catch(err => {
        this.onAlertEvent.emit({
          title: 'Error',
          message: this.error(err),
          type: 'error'
        });
      });
  }
  updateProgress() {
    this.http.updateProgress(this.trStatus.value, this.trEventId2)
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
  onStatusSelectionChanged(e) {
    console.log(this.trStatus.value);
  }

  // =========================== Ticket ===========================
  getTicketAvailability() {
    this.http.getTicketAvailability(this.eventId)
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
  buyTicket() {
    this.http.buyTicket(this.eventId, this.name, this.numberOfTickets)
    .then(result => {
      // @ts-ignore
      this.totalPrice = result.totalPrice;
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
  payTicket() {
    this.http.payTicket(this.eventId, this.name, this.numberOfTickets, this.totalPrice)
      .then(result => {
        this.ticketId = result[0].ticketId;
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
  validateTicket() {
    this.http.validateTicket(this.ticketId)
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

  // =========================== Badge ===========================
  rechargeBadge() {
    this.http.rechargeBadge(this.badgeId, this.amount)
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

  // =========================== Food and Drinks ===========================
  createOrder() {
    this.http.createOrder(this.eventId, this.badgeId, this.lineItems)
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

  // =========================== Cloakroom ===========================
  addCloakroomItem() {
    this.http.addCloakroomItem(this.crBadgeId)
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
  removeCloakroomItem() {
    this.http.removeCloakroomItem(this.crItemId)
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
  getAllCloakroomItems() {
    this.http.getAllCloakroomItems()
      .then(result => {
        this.onAlertEvent.emit({
          title: 'Success',
          message: result,
          type: 'success'
        });
        this.crItems = result as any[];
      })
      .catch(err => {
        this.onAlertEvent.emit({
          title: 'Error',
          message: this.error(err),
          type: 'error'
        });
      });
  }

  // =========================== Multimedia ===========================
  updateInformationBoards() {
    this.http.updateInformationBoards(this.message)
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


  // =========================== Security ===========================
  triggerEmergency() {
    this.http.triggerEmergency(this.type, this.severity, this.source)
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
}
