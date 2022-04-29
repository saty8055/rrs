import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TooltipModule } from 'ng2-tooltip-directive';
import { TpInterceptorServicer } from './services/tp-interceptor.service';
import { LoaderComponent } from './loader/loader.component';
import { F04Component } from './f04/f04.component';
import { DatePipe } from '@angular/common';
import { ProfileComponent } from './dashboard/profile/profile.component';
import { ResetComponent } from './reset/reset.component';
import { RoomComponent } from './dashboard/room/room.component';
import { BookingComponent } from './dashboard/booking/booking.component';
import { RegisterComponent } from './register/register.component';
import { PaymentHistoryComponent } from './dashboard/payment-history/payment-history.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    LoaderComponent,
    F04Component,
    ProfileComponent,
    ResetComponent,
    RoomComponent,
    BookingComponent,
    RegisterComponent,
    PaymentHistoryComponent,
  ],
  imports: [
    BrowserModule,
    TooltipModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [  
    DatePipe,
    {
    provide: HTTP_INTERCEPTORS,
    useClass: TpInterceptorServicer,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
