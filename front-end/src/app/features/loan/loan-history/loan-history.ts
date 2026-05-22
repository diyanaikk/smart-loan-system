import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { UserNavbar } from '../../user/user-navbar/user-navbar';

@Component({
  selector: 'app-loan-history',
  standalone: true,
  imports: [CommonModule, UserNavbar],
  templateUrl: './loan-history.html',
  styleUrl: './loan-history.css'
})
export class LoanHistory implements OnInit {

  loans: any[] = [];
  filter = 'ALL';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:9090/loans').subscribe({
      next: (data) => {
        this.loans = [...data];
      },
      error: (err) => console.log(err)
    });
  }

  filteredLoans() {
    if (this.filter === 'ALL') return this.loans;
    return this.loans.filter(loan => loan.status === this.filter);
  }

  countBy(status: string) {
    return this.loans.filter(l => l.status === status).length;
  }

}