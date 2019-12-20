import { Component } from '@angular/core';
import {FormControl} from '@angular/forms';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Uitbating evenementencomplex';
  startDate = new FormControl(new Date());
  endDate = new FormControl(new Date());
}
