import { Routes } from '@angular/router';
import { LoginComponent } from './admin/login/login.component';
import { AdminCardComponent } from './admin/shared/admin-card.component';
import { NailServicesComponent } from './admin/nail-services/nail-services.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AppointmentsComponent } from './admin/appointments/appointments.component';
import { EmployeesComponent } from './admin/employees/employees.component';
import { CustomersComponent } from './admin/customers/customers.component';
import { CustomerAppointmentsComponent } from './admin/customers/customer-appointments/customer-appointments.component';
import { CustomerRegistrationComponent } from './customer-side/customer-registration/customer-registration.component';
import { CustomerLoginComponent } from './customer-side/customer-login/customer-login.component';
import { CustomerCardComponent } from './customer-side/shared/customer-card/customer-card.component';
import { CustomerAppointmentComponent } from './customer-side/customer-appointment/customer-appointment.component';

export const routes: Routes = [
  {
    path: 'admin/login', 
    component: LoginComponent,
    title: "Login Page"
  },
  {
    path: 'admin', 
    component: AdminCardComponent,
    title: "Admin Page",
    children: [
      {
        path: "appointments",
        component: AppointmentsComponent
      },
      {
        path: 'employees',
        component: EmployeesComponent
      },
      {
        path: 'customers',
        component: CustomersComponent
      },
      {
        path: 'customers/:customerId/appointments',
        component: CustomerAppointmentsComponent
      },
      {
        path: 'services',
        component: NailServicesComponent
      }
    ]
  },
  {
    path: 'customer/register', 
    component: CustomerRegistrationComponent,
    title: 'Register Member'
  },
  {
    path: 'customer/login', 
    component: CustomerLoginComponent,
    title: 'Customer Login Page'
  },
  {
    path: 'customer', component: CustomerCardComponent,
    children: [
      {
        path: 'appointment', 
        component: CustomerAppointmentComponent
      }
    ]
  },
  {
    path: '**', component: NotFoundComponent
  }
];
