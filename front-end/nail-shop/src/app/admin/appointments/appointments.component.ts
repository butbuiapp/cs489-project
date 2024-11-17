import { Component, inject, OnInit } from '@angular/core';
import { AppointmentResponse } from '../../model/appointment.model';
import { AppointmentService } from '../../service/appointment.service';
import { CommonModule } from '@angular/common';
import { AppointmentComponent } from './appointment/appointment.component';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointments',
  standalone: true,
  imports: [CommonModule, AppointmentComponent, ReactiveFormsModule],
  templateUrl: './appointments.component.html',
  styleUrl: './appointments.component.css'
})
export class AppointmentsComponent implements OnInit {
  appointments: AppointmentResponse[] = [];
  errorMessage: string | null = null;
  appointmentDate = new FormControl(new Date());

  private appointmentService = inject(AppointmentService);

  ngOnInit(): void {  
    this.searchAppointmentsByDate(new Date());
  }

  searchAppointmentsByDate(appointmentDate: Date) {
    this.appointmentService.searchAppointmentsByDate({appointmentDate}).subscribe(
      (response) => {
        this.appointments = response;
      },
      () => {
        this.errorMessage = "Failed to load appointments.";
      }
    );
  }

  onSearch(): void {
    const date = this.appointmentDate.value;
    if (date) {
      this.searchAppointmentsByDate(date);
    }
  }


  trackByAppointmentId(index: number, appointment: AppointmentResponse): number {
    return appointment.id;
  }
}
