import { Injectable } from "@angular/core";
import { Constants } from "../common/constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = Constants.CUSTOMER_URL;

  constructor (private http: HttpClient) {}

  getCustomers() : Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  searchCustomerByPhone(phoneNumber: string) : Observable<any> {
    return this.http.get<any>(this.apiUrl + '/search/' + phoneNumber);
  }
}