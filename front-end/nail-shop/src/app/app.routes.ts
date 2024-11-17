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
import { MyAppointmentsComponent } from './customer-side/my-appointments/my-appointments.component';
import { CustomerChangePasswordComponent } from './customer-side/customer-change-password/customer-change-password.component';
import { CustomerProfileComponent } from './customer-side/customer-profile/customer-profile.component';
import { NewAppointmentComponent } from './customer-side/my-appointments/new-appointment/new-appointment.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'customer/login',
    pathMatch: 'full'
  },
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
    path: 'customer', 
    component: CustomerCardComponent,
    children: [
      {
        path: 'my-appointments', 
        component: MyAppointmentsComponent,
        title: 'My Appointments'
      },
      {
        path: 'new-appointment', 
        component: NewAppointmentComponent,
        title: 'New Appointment'
      },
      {
        path: 'profile', 
        component: CustomerProfileComponent,
        title: 'My Profile'
      },
      {
        path: 'change-password', 
        component: CustomerChangePasswordComponent,
        title: 'Change Password'
      }
    ]
  },
  {
    path: '**', component: NotFoundComponent
  }
];
