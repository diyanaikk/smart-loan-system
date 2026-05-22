import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';

import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';

import { UserNavbar } from '../user-navbar/user-navbar';

@Component({
  selector: 'app-user-dashboard',

  standalone: true,

  imports: [
    CommonModule,
    UserNavbar
  ],

  templateUrl: './user-dashboard.html',

  styleUrls: ['./user-dashboard.css']
})

export class UserDashboardComponent
implements OnInit {

  loans: any[] = [];

  username: string = '';

  creditScore: number = 0;

  approvedCount: number = 0;

  pendingCount: number = 0;

  rejectedCount: number = 0;

  activeLoans: number = 0;

  constructor(
    private http: HttpClient
  ) {}

  ngOnInit(): void {

    this.username =
      localStorage.getItem('username') || '';

    setTimeout(() => {

      this.loadDashboardData();

    }, 300);

  }

  loadDashboardData() {

    const token =
      localStorage.getItem('token');

    const userId = Number(
      localStorage.getItem('userId')
    );

    const headers = new HttpHeaders({

      Authorization: `Bearer ${token}`

    });

    // LOAD USER LOANS
    this.http.get<any[]>(

      `http://localhost:9090/loans/user/${userId}`,

      { headers }

    ).subscribe({

      next: (data) => {

        console.log(data);

        this.loans = data.reverse();

        this.approvedCount =
          data.filter(
            loan => loan.status === 'APPROVED'
          ).length;

        this.pendingCount =
          data.filter(
            loan => loan.status === 'PENDING'
          ).length;

        this.rejectedCount =
          data.filter(
            loan => loan.status === 'REJECTED'
          ).length;

        this.activeLoans =
          this.approvedCount;

      },

      error: (err) => {

        console.log(err);

      }

    });

    // LOAD CREDIT SCORE
    this.http.get<any>(

      `http://localhost:9090/credit/${userId}`,

      { headers }

    ).subscribe({

      next: (scoreData) => {

        console.log(scoreData);

        if(scoreData) {

          this.creditScore =
            scoreData.score;

        }

      },

      error: (err) => {

        console.log(err);

      }

    });

  }

  getRejectReason(
    loan: any
  ): string {

    if(loan.salary < 20000) {

      return 'Salary below eligibility criteria';

    }

    if(loan.amount > 500000) {

      return 'Loan amount exceeds limit';

    }

    if(loan.tenure > 10) {

      return 'Tenure exceeds limit';

    }

    return 'Loan eligibility criteria not met';

  }

}