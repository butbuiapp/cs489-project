import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-header',
  standalone: true,
  imports: [],
  templateUrl: './customer-header.component.html',
  styleUrl: './customer-header.component.css'
})
export class CustomerHeaderComponent implements OnInit {
  private authService = inject(AuthService);
  private router = inject(Router);

  loginUser: string | null = null;

  ngOnInit(): void {
    this.loginUser = this.authService.getLoggedInUser();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['./customer/login']);
  }
}
