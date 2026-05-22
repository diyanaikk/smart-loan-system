import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private baseUrl = 'http://localhost:9090/auth';

  constructor(private http: HttpClient) {

  }

  login(username: string): Observable<string> {

  return this.http.post(
    `${this.baseUrl}/login?username=${username}`,
    {},
    {
      responseType: 'text'
    }
  );

}

}


//when Angular calls: login("diya")
//-> it sends: POST http://localhost:9090/auth/login?username=diya
