import { Injectable } from "@angular/core";
import { Constants } from "../common/constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { AppointmentSearch } from "../model/appointment.model";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private apiUrl = Constants.APPOINTMENT_URL;

  constructor (private http: HttpClient) {}

  getAppointmentsByCustomer(customerId: number) : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/customer/${customerId}`);
  }

  searchAppointmentsByDate(appointmentSearch: AppointmentSearch) : Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/search`, appointmentSearch);
  }
}