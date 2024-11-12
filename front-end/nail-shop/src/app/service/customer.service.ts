import { Injectable } from "@angular/core";
import { Constants } from "../common/constants";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Customer } from "../model/customer.model";
import { ChangePasswordDto } from "../model/change-password.model";

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
    return this.http.get<string>(this.apiUrl + '/search/' + phoneNumber);
  }

  createCustomer(customer: Customer) : Observable<any> {
    return this.http.post<Customer>(this.apiUrl, customer);
  }

  changePassword(id: number, changePasswordDto: ChangePasswordDto): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/change-password/${id}`, changePasswordDto);
  }

  updateCustomer(id: number, customer: Customer) : Observable<any> {
    return this.http.put<Customer>(`${this.apiUrl}/${id}`, customer);
  }
}