import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
/***
 * @description Service to perform API related Ops...
 */
export class ApiService {

  constructor(private http: HttpClient) { }

  private HOST: string = "http://localhost:8060";

  private SECURE: string = this.HOST + "/api";

  private LOGIN_URL = this.HOST + "/login";

  private LOOKUP_URL = this.HOST + "/lookup";

  private PROFILE_URL = this.SECURE + "/profile";

  private BOOKING_URL = this.SECURE + "/booking";

  private ROOM_URL = this.SECURE + "/room";

  private FORGOT_URL = this.HOST + "/public/forgot";

  private RESET_URL = this.HOST + "/public/reset";

  public book(booking:any): Observable<any> {
    return this.http.post(this.BOOKING_URL, booking);
  }

  public listBookings(pageNumber:number, pageSize:number, sortBy:string, sortDirection:string):Observable<any> {
    return this.http.get(this.BOOKING_URL+"/mine?pageNumber="+pageNumber+"&pageSize="+pageSize+"&sortBy="+sortBy+"&sortDirection="+sortDirection);
  }

  public listRooms(bookingFrom:string, bookingTo:string):Observable<any> {
    return this.http.get(this.ROOM_URL+"/query?from="+bookingFrom+"&to="+bookingTo);
  }

  public cancel(bookingId:string):Observable<any> {
    return this.http.put(this.BOOKING_URL+"/"+bookingId, {});
  }

  public forgot(username: string): Observable<any> {
    return this.http.get(this.FORGOT_URL + "/" + username);
  }

  public reset(password: string, token: string): Observable<any> {
    return this.http.post(this.RESET_URL + "/" + token, { "password": password });
  }

  public login(form): Observable<any> {
    return this.http.post(this.LOGIN_URL, form);
  }

  isLoggedIn(): boolean {
    let token = localStorage.getItem("currentToken");
    return !(token == null || token == "null" || token == "undefined" || token == undefined);
  }

  public lookUpUsername(username: string): Observable<any> {
    return this.http.get(this.LOOKUP_URL + "/username/" + username);
  }

  public lookUpEmail(email: string): Observable<any> {
    return this.http.get(this.LOOKUP_URL + "/email/" + email);
  }

  public getUser(): Observable<any> {
    return this.http.get(this.PROFILE_URL);
  }

  public updatePassword(newPassword: string, oldPassword: string): Observable<any> {
    return this.http.put(this.PROFILE_URL, { password: newPassword, oldPassword: oldPassword });
  }

  public validateResponse(response: any, successCall, failureCall) {
    try {
      var str = JSON.stringify(response);
      JSON.parse(str);
    } catch (e) {
      failureCall("Unexpected Error!");
    }
    if (response.error)
      failureCall(response.message);
    else
      successCall(response);
  }

}
