import { Routes } from '@angular/router';

import { Login } from './features/auth/login/login';

import { Register } from './features/auth/register/register';

import { UserDashboardComponent } from './features/user/user-dashboard/user-dashboard';

import { AdminDashboard } from './features/admin/admin-dashboard/admin-dashboard';

import { ApplyLoanComponent } from './features/loan/apply-loan/apply-loan';

import { LoanHistory } from './features/loan/loan-history/loan-history';

import { authGuard } from './core/guards/auth.guard';

import { PendingLoans } from './features/admin/pending-loans/pending-loans';

import { RepaymentStatus } from './features/admin/repayment-status/repayment-status';

import { LoanRepaymentComponent } from './features/user/loan-repayment/loan-repayment';

export const routes: Routes = [

  {
    path: '',
    component: Login
  },

  {
    path: 'login',
    component: Login
  },

  {
    path: 'register',
    component: Register
  },

  {
  path: 'loan-repayment',
  component: LoanRepaymentComponent,
  canActivate: [authGuard]
},

  {
    path: 'user-dashboard',
    component: UserDashboardComponent,
    canActivate: [authGuard]
  },

  {
    path: 'admin-dashboard',
    component: AdminDashboard,
    canActivate: [authGuard]
  },

 {
  path: 'apply-loan',
  component: ApplyLoanComponent,
  canActivate: [authGuard]
},

{
  path: 'user/loan-history',
  component: LoanHistory,
  canActivate: [authGuard]
},

  {
  path: 'pending-loans',
  component: PendingLoans,
  canActivate: [authGuard]
},

{
  path: 'repayment-status',
  component: RepaymentStatus,
  canActivate: [authGuard]
}

];