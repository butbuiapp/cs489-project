import { Injectable } from "@angular/core";
import { Constants } from "../common/constants";
import { LoginRequestDto, LoginResponseDto } from "../model/login.model";
import { Observable, BehaviorSubject } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { jwtDecode, JwtPayload } from "jwt-decode";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  private apiUrl = Constants.AUTH_URL;

  private loginDataSubject = new BehaviorSubject<any>(null);
  public loginData$ = this.loginDataSubject.asObservable();

  constructor (private http : HttpClient) {
    const accessToken = localStorage.getItem('authToken');
    if (accessToken) {
      // decode token
      const decodedToken: any = this.getDecodedToken(accessToken);
      if (decodedToken) {
        this.loginDataSubject.next(decodedToken);
      }    
    }
  }

  customerLogin(login: LoginRequestDto) : Observable<any> {
    return this.http.post(`${this.apiUrl}/customer/login`, login);
  }

  adminLogin(login: LoginRequestDto) : Observable<any> {
    return this.http.post(`${this.apiUrl}/admin/login`, login);
  }

  storeLoginInfo(loginResponse: LoginResponseDto) {
    // Store in localStorage
    localStorage.setItem('authToken', loginResponse.accessToken);
    // decode token
    const decodedToken: any = this.getDecodedToken(loginResponse.accessToken);
    if (decodedToken) {
      this.loginDataSubject.next(decodedToken);
    }   
  }

  getDecodedToken(token: string): { username: string } | null {
    try {
      const decodedToken: any = jwtDecode(token);
      return {
        username: decodedToken.sub
      };
    } catch (error) {
      console.error('Error decoding token', error);
      return null;
    }
  }

  isTokenExpired(token: string): boolean {
    const decodedToken = jwtDecode(token);
    if (!decodedToken || !decodedToken.exp) return true;
  
    const expirationDate = new Date(0);
    expirationDate.setUTCSeconds(decodedToken.exp);
  
    return expirationDate < new Date();
  }
  
  getLoggedInUser() {
    return this.loginDataSubject.value?.username;
  }

  getLoggedInUsername() {
    return this.loginDataSubject.value.username;
  }

  logout() {
    localStorage.removeItem('authToken');
    this.loginDataSubject.next(null);
  }
}