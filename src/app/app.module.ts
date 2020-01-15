import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  MatButtonModule,
  MatButtonToggleModule,
  MatDatepickerModule,
  MatInputModule,
  MatNativeDateModule, MatRadioModule, MatSelectModule,
  MatTabsModule
} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {APP_DATE_FORMATS} from './date.adapter';
import {ToastrModule} from 'ngx-toastr';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatButtonToggleModule,
    MatTabsModule,
    MatButtonModule,
    MatSelectModule,
    ToastrModule.forRoot(),
    MatRadioModule
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'nl-BE'},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
