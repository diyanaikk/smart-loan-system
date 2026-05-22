import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminNavbar } from '../admin-dashboard/admin-navbar/admin-navbar';

@Component({
  selector: 'app-repayment-status',
  standalone: true,
  imports: [CommonModule, AdminNavbar],
  templateUrl: './repayment-status.html',
  styleUrl: './repayment-status.css'
})
export class RepaymentStatus {

  repayments = [
    {
      loanId: 1,
      userId: 1,
      emi: 5000,
      dueDate: '2026-06-01',
      status: 'PENDING'
    },
    {
      loanId: 2,
      userId: 2,
      emi: 8000,
      dueDate: '2026-06-10',
      status: 'PAID'
    }
  ];

}