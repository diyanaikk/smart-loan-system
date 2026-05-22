import { AdminService } from '../../../core/services/admin.service';
import { CommonModule } from '@angular/common';
import { AdminNavbar } from './admin-navbar/admin-navbar';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, AdminNavbar],
  templateUrl: './admin-dashboard.html',
  styleUrls: ['./admin-dashboard.css']
})
export class AdminDashboard implements OnInit {

  totalLoans = 0;
  approvedLoans = 0;
  pendingLoans = 0;
  rejectedLoans = 0;

  loans: any[] = [];

  constructor(private service: AdminService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.getAllLoans();
  }

  getAllLoans() {
    this.service.getAllLoans().subscribe({
      next: (data: any) => {
        this.loans = [...data];

        this.totalLoans = this.loans.length;

        this.approvedLoans = this.loans.filter(
          (loan: any) => loan.status === 'APPROVED'
        ).length;

        this.pendingLoans = this.loans.filter(
          (loan: any) => loan.status === 'PENDING'
        ).length;

        this.rejectedLoans = this.loans.filter(
          (loan: any) => loan.status === 'REJECTED'
        ).length;

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  approveLoan(id: number) {
    this.service.approveLoan(id).subscribe({
      next: () => {
        alert("Loan Approved Successfully");
        this.getAllLoans();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  rejectLoan(id: number) {
    this.service.rejectLoan(id).subscribe({
      next: () => {
        alert("Loan Rejected Successfully");
        this.getAllLoans();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

}