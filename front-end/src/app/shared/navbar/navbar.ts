import { Component } from '@angular/core';

import { RouterLink } from '@angular/router';

import { Router } from '@angular/router';

@Component({

  selector: 'app-navbar',

  standalone: true,

  imports: [RouterLink],

  templateUrl: './navbar.html',

  styleUrl: './navbar.css'

})

export class Navbar {
  constructor(private router: Router) {

  }
  logout() {

    localStorage.clear();

    this.router.navigate(['/']);

  }
}