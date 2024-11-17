import { Component, inject, OnInit } from '@angular/core';
import { NailServiceService } from '../../../service/nail-service.service';
import { EmployeeService } from '../../../service/employee.service';
import { NailService } from '../../../model/nail-service.model';
import { Employee } from '../../../model/employee.model';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Appointment, AppointmentForm, AppointmentRequest } from '../../../model/appointment.model';
import { AuthService } from '../../../service/auth.service';
import { AppointmentService } from '../../../service/appointment.service';

@Component({
  selector: 'app-new-appointment',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-appointment.component.html',
  styleUrl: './new-appointment.component.css'
})
export class NewAppointmentComponent implements OnInit {
  services : NailService[] = [];
  employees : Employee[] = [];
  errorMessage : string | null = null;
  infoMessage : string | null = null;
  totalAmount: number = 0;
  phoneNumber : string = "";
  appointmentForm!: FormGroup;

  private employeeService = inject(EmployeeService);
  private nailServiceService = inject(NailServiceService);
  private authService = inject(AuthService);
  private appointmentService = inject(AppointmentService);

  constructor(private fb: FormBuilder) {
    this.appointmentForm = this.fb.group({
      date: ['', Validators.required],
      time: ['', Validators.required],
      employee: ['', Validators.required],
      selectedServices: this.fb.array([]),
      notes: [''],
    });
  }

  ngOnInit(): void {
    this.phoneNumber = this.authService.getLoggedInUsername();
    this.loadNailServices();
    this.loadEmployees();
  }

  loadNailServices() {
    this.nailServiceService.getNailServices().subscribe(
      (response) => {
        this.services = response;
      },
      (error) => {
        this.errorMessage = 'Failed to load services.'
      }
    );
  }

  loadEmployees() {
    this.employeeService.getEmployees().subscribe(
      (response) => {
        this.employees = response;
      },
      (error) => {
        this.errorMessage = 'Failed to load employees.'
      }
    );
  }

  // Total calculation
  calculateTotal() {
    this.totalAmount = this.appointmentForm.value.selectedServices
      .map((service: NailService) => service.price)
      .reduce((a: number, b: number) => a + b, 0);
  }

  // To handle service selection and calculate total
  onServiceChange(event: any, service: any) {
    const selectedServices = this.appointmentForm.get('selectedServices') as FormArray;
    if (event.target.checked) {
      selectedServices.push(this.fb.control(service));
    } else {
      const index = selectedServices.controls.findIndex((x) => x.value === service);
      selectedServices.removeAt(index);
    }
    this.calculateTotal();
  }

  // Submit handler
  onSubmit() {
    if (this.appointmentForm.valid) {
      console.log('Appointment booked:', this.appointmentForm.value);
      const appointmentForm = this.appointmentForm.value;

      // convert appointmentForm into Appointment
      const appointmentRequest: AppointmentRequest = 
                this.convertAppointmentFormToRequest(appointmentForm, this.totalAmount);

      // save appointment
      this.appointmentService.createAppointment(appointmentRequest).subscribe(
        (response) => {
          this.infoMessage = response.message;
          this.appointmentForm.reset();
        },
        (error) => {
          Object.keys(error).forEach(key => {
            console.log(`${key}: ${error[key as keyof typeof error]}`);
          });

          this.errorMessage = error;
          if (error.error) {
            this.errorMessage = error.error;            
          }         
        }
      );

    } else {
      this.errorMessage = 'Input is invalid.';
    }
  }

  convertAppointmentFormToRequest(
    form: AppointmentForm,
    totalAmount: number
  ): AppointmentRequest {
    const appointmentRequest: AppointmentRequest = {
      date: form.date,
      time: form.time,
      technician: {
        id: form.employee,
      },
      customer: {
        phoneNumber: this.phoneNumber, // Assuming `phoneNumber` is passed as a parameter
      },
      notes: form.notes,
      invoice: form.selectedServices.length > 0
      ? {
          issuedDate: new Date().toISOString(),
          totalAmount: totalAmount,
          services: form.selectedServices.map((service) => ({ id: service.id, price: service.price })),
        }
      : null,
    };
    
    return appointmentRequest;
  }

}
