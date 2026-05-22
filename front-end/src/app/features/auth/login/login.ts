import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';

import { Router, RouterModule } from '@angular/router';

import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',

  standalone: true,

  imports: [FormsModule, RouterModule, CommonModule],

  templateUrl: './login.html',

  styleUrl: './login.css'
})

export class Login {

  username: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onLogin() {

    this.authService.login(this.username)
      .subscribe({

        next: (response) => {

          console.log(response);

          localStorage.setItem(
            'token',
            response
          );

          localStorage.setItem(
            'username',
            this.username
          );

          // CONVERT TO LOWERCASE
          const username =
            this.username.toLowerCase();

          // TEMP USER IDS
          if(username === 'admin') {

            localStorage.setItem(
              'userId',
              '0'
            );

          }
          else if(username === 'diya') {

            localStorage.setItem(
              'userId',
              '1'
            );

          }
          else if(username === 'alex') {

            localStorage.setItem(
              'userId',
              '2'
            );

          }
          else if(username === 'sonali') {

            localStorage.setItem(
              'userId',
              '5'
            );

          }
          else if(username === 'belle') {

            localStorage.setItem(
              'userId',
              '6'
            );

          }
          else {

            alert('User not mapped');

            return;

          }

          console.log(
            'Stored UserId:',
            localStorage.getItem('userId')
          );


          // NAVIGATION
          if(username === 'admin') {

            this.router.navigate([
              '/admin-dashboard'
            ]);

          }
          else {

            this.router.navigate([
              '/user-dashboard'
            ]);

          }

        },

        error: (error) => {

          console.log(error);

          console.log("Login Failed");

        }

      });

  }

}