import { Injectable } from "@angular/core";
import { Constants } from "../common/constants";
import { LoginRequestDto, LoginResponseDto } from "../model/login.model";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  private apiUrl = Constants.AUTH_URL;

  constructor (private http : HttpClient) {}

  customerLogin(login: LoginRequestDto) : Observable<any> {
    return this.http.post(`${this.apiUrl}/customer/login`, login);
  }

  adminLogin(login: LoginRequestDto) : Observable<any> {
    return this.http.post(`${this.apiUrl}/admin/login`, login);
  }

  storeLoginInfo(loginResponse: LoginResponseDto) {
    // Store in localStorage
    localStorage.setItem('accessToken', loginResponse.accessToken);
    localStorage.setItem('expiresIn', loginResponse.toString());
  }

  logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('expiresIn');
  }
}