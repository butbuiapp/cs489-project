import { Component, input, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Appointment } from '../../../model/appointment.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [CommonModule, DatePipe],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent {
  // @Input() appointment: Appointment | null = null;
  appointment = input.required<Appointment>();

  showInvoiceMap: { [appointmentId: number]: boolean } = {};

  toggleInvoice(appointmentId: number) {
    this.showInvoiceMap[appointmentId] = !this.showInvoiceMap[appointmentId];
  }
}
