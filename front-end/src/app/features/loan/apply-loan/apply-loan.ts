import { Component } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { CommonModule } from '@angular/common';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Router } from '@angular/router';

import { UserNavbar } from '../../user/user-navbar/user-navbar';

@Component({
  selector: 'app-apply-loan',

  standalone: true,

  imports: [
    FormsModule,
    CommonModule,
    UserNavbar
  ],

  templateUrl: './apply-loan.html',

  styleUrl: './apply-loan.css'
})

export class ApplyLoanComponent {

  successMsg: string = '';

  errorMsg: string = '';

  loanData = {

    userId: 0,

    applicantName: '',

    amount: 0,

    salary: 0,

    loanType: '',

    tenure: 0

  };

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  applyLoan() {

    const token = localStorage.getItem('token');

    const userId = localStorage.getItem('userId');

    this.loanData.userId = Number(userId);

    const headers = new HttpHeaders({

      Authorization: `Bearer ${token}`

    });

    this.http.post(

      'http://localhost:9090/loans/apply',

      this.loanData,

      { headers }

    ).subscribe({

      next: (response: any) => {

        console.log(response);

        this.successMsg =
          'Loan Application Submitted Successfully';

        this.errorMsg = '';

        setTimeout(() => {

          this.router.navigateByUrl(
            '/user-dashboard'
          );

        }, 1000);

      },

      error: (error) => {

        console.log(error);

        this.successMsg = '';

        this.errorMsg =
          'Loan Application Failed';

      }

    });

  }

}