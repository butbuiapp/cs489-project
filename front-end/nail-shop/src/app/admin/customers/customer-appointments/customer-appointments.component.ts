import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentService } from '../../../service/appointment.service';
import { CommonModule } from '@angular/common';
import { Appointment } from '../../../model/appointment.model';
import { AppointmentComponent } from '../../appointments/appointment/appointment.component';

@Component({
  selector: 'app-customer-appointments',
  standalone: true,
  imports: [CommonModule, AppointmentComponent],
  templateUrl: './customer-appointments.component.html',
  styleUrl: './customer-appointments.component.css'
})
export class CustomerAppointmentsComponent implements OnInit {
  customerId: number | null = null;
  appointments: Appointment[] = [];
  errorMessage: string | null = null;

  firstName: string = '';
  lastName: string = '';
  
  private activatedRoute = inject(ActivatedRoute);
  private appointmentService = inject(AppointmentService);

  constructor(private router: Router) {
    // only work inside constructor
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as { firstName: string; lastName: string };
    if (state) {
      this.firstName = state.firstName;
      this.lastName = state.lastName;
    }
  }

  ngOnInit(): void {
      this.customerId = Number(this.activatedRoute.snapshot.paramMap.get('customerId'));

      // get customer's appointments
      this.appointmentService.getAppointmentsByCustomer(this.customerId).subscribe(
        (response) => {
          this.appointments = response;
        },
        () => {
          this.errorMessage = "Failed to load appointments.";
        }
      );
  }

  showInvoiceMap: { [appointmentId: number]: boolean } = {};

  toggleInvoice(appointmentId: number) {
    this.showInvoiceMap[appointmentId] = !this.showInvoiceMap[appointmentId];
  }

  trackByAppointmentId(index: number, appointment: Appointment): number {
    return appointment.id;
  }
}
