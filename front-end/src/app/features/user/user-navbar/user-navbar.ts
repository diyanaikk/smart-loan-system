import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './user-navbar.html',
  styleUrl: './user-navbar.css'
})
export class UserNavbar {

  constructor(private router: Router) {}

  logout() {

    localStorage.clear();

    this.router.navigate(['/login']);

  }

}