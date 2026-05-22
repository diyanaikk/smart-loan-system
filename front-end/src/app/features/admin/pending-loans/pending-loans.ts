import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { AdminNavbar } from '../admin-dashboard/admin-navbar/admin-navbar';

@Component({
  selector: 'app-pending-loans',
  standalone: true,
  imports: [CommonModule, AdminNavbar],
  templateUrl: './pending-loans.html',
  styleUrl: './pending-loans.css'
})
export class PendingLoans implements OnInit {

  loans: any[] = [];

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.getLoans();
  }

  getLoans() {
    this.http.get<any[]>('http://localhost:9090/loans').subscribe({
      next: (data) => {
        this.loans = [...data].filter(loan => loan.status === 'PENDING');
        this.cdr.detectChanges();
      },
      error: (err) => console.log(err)
    });
  }

  approveLoan(id: number) {
    this.http.put(`http://localhost:9090/loans/${id}/approve`, {}).subscribe({
      next: () => {
        alert('✅ Loan Approved!');
        this.getLoans();
      },
      error: (err) => console.log(err)
    });
  }

  rejectLoan(id: number) {
    this.http.put(`http://localhost:9090/loans/${id}/reject`, {}).subscribe({
      next: () => {
        alert('❌ Loan Rejected!');
        this.getLoans();
      },
      error: (err) => console.log(err)
    });
  }

}