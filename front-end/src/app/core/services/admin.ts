import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AdminService{

  constructor(private http: HttpClient) {}

    getAllLoans() {
      return this.http.get('http://localhost:9090/loans');
    }

    approveLoan(id: number) {
      return this.http.put(
        `http://localhost:9090/loans/approve/${id}`,
        {}
      );
    }

    rejectLoan(id: number) {
      return this.http.put(
        `http://localhost:9090/loans/reject/${id}`,
        {}
      );
    }

}
