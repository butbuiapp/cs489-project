import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-customer-profile',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './customer-profile.component.html',
  styleUrl: './customer-profile.component.css'
})
export class CustomerProfileComponent implements OnInit {
  errorMessage: string | null = null;
  infoMessage: string | null = null;
  customerForm! : FormGroup;
  private formBuilder = inject(FormBuilder);
  private customerService = inject(CustomerService);

  ngOnInit(): void {
    this.customerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      email: ['', [Validators.email]],
      dob: ['', []],
    });
  }

  onSubmit() {
    this.errorMessage = null; 
    if (this.customerForm.valid) {
      const customer = this.customerForm.value;
      // save customer
      this.customerService.createCustomer(customer).subscribe(
        (response) => {
          this.infoMessage = response;
          this.customerForm.reset();
        },
        (error) => {
          if (error.error) {
            this.errorMessage = error.error;            
          }         
        }
      );
    }  else {
      this.errorMessage = 'Input is invalid.';
    }
  }

}
