import { Component } from '@angular/core';

import { FormsModule } from '@angular/forms';

import { CommonModule } from '@angular/common';

import { HttpClient } from '@angular/common/http';

import { Router } from '@angular/router';

@Component({
  selector: 'app-register',

  standalone: true,

  imports: [
    FormsModule,
    CommonModule
  ],

  templateUrl: './register.html',

  styleUrl: './register.css'
})

export class Register {

  successMsg: string = '';

  errorMsg: string = '';

  registerData = {

    name: '',

    username:'',

    password:'',

    email: '',

    salary: 0,

    employmentType: ''

  };

  constructor(

    private http: HttpClient,

    private router: Router

  ) {}

  registerUser() {

    this.http.post(

      'http://localhost:8081/users',

      this.registerData

    ).subscribe({

      next: (response: any) => {

        console.log(response);

        this.successMsg =
          'Account Created Successfully';

        this.errorMsg = '';

        setTimeout(() => {

          this.router.navigate([
            '/login'
          ]);

        }, 1500);

      },

      error: (error) => {

        console.log(error);

        this.successMsg = '';

        this.errorMsg =
          'Registration Failed';

      }

    });

  }

}