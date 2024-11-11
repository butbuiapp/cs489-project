import { Customer } from "./customer.model";
import { Employee } from "./employee.model";
import { Invoice } from "./invoice.model";


// Define AppointmentResponseDto interface
export interface Appointment {
  id: number;
  date: string;
  time: string;
  customer: Customer
  technician: Employee;
  notes: string;
  invoice: Invoice | null;         // Invoice is optional (can be null)
}

export interface AppointmentSearch {
  appointmentDate: Date;
}
