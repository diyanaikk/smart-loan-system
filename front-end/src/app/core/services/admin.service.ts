import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseUrl = 'http://localhost:9090';

  constructor(private http: HttpClient) {}

  getAllLoans(): Observable<any> {
    return this.http.get(`${this.baseUrl}/loans`);
  }

  approveLoan(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/loans/${id}/approve`, {});
  }

  rejectLoan(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/loans/${id}/reject`, {});
  }

}