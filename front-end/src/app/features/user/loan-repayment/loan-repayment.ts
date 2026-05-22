import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PaymentService } from '../../payment/payment.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-loan-repayment',
  standalone: true,
  imports: [
  CommonModule,
  FormsModule,
  RouterModule
],
  templateUrl: './loan-repayment.html',
  styleUrls: ['./loan-repayment.css']
})
export class LoanRepaymentComponent implements OnInit {

  approvedLoans: any[] = [];

  selectedLoan: any = null;
  selectedEmi: any = null;

  showPaymentPopup = false;

  card1 = '';
  card2 = '';
  card3 = '';
  card4 = '';

  cardName = '';
  expiry = '';
  cvv = '';

  constructor(
    private paymentService: PaymentService
  ) {}

  ngOnInit(): void {

    const userId =
      Number(localStorage.getItem('userId'));

    this.paymentService
      .getApprovedLoans(userId)
      .subscribe({

        next: (data: any[]) => {

          console.log('API DATA:', data);

          // FILTER ONLY APPROVED LOANS

          const approved =
  data.filter((loan: any) => {

    return (

      loan.status &&
      String(loan.status)
        .trim()
        .toUpperCase() === 'APPROVED'

    );

  });

          // IF USER HAS APPROVED LOANS

          if (approved.length > 0) {

            this.approvedLoans =
              approved.map((loan: any) => {

                const emiAmount =
                  Math.round(
                    Number(loan.amount) /
                    (Number(loan.tenure) * 12)
                  );

                  console.log('APPROVED LOANS:', approved);

                const emis: any[] = [];

                for (
                  let i = 1;
                  i <= loan.tenure * 12;
                  i++
                ) {

                  emis.push({

                    emiNumber: i,

                    amount: emiAmount,

                    status:
                      i === 1
                        ? 'PAID'
                        : 'PENDING'

                  });

                }

                return {

                  ...loan,

                  emis: emis,

                  remainingBalance:
  loan.remainingBalance ??
  (loan.amount - emiAmount)

                };

              });

          }

          // NO APPROVED LOANS

          else {

            this.approvedLoans = [];

          }

        },

        error: (err) => {

          console.log(err);

          this.approvedLoans = [];

        }

      });

  }

  openPaymentPopup(
    loan: any,
    emi: any
  ): void {

    // ALREADY PAID

    if (emi.status === 'PAID') {
      return;
    }

    // LOCK FUTURE EMI

    if (

      emi.emiNumber > 1 &&

      loan.emis[emi.emiNumber - 2].status !== 'PAID'

    ) {

      alert(
        'Please pay previous EMI first'
      );

      return;
    }

    this.selectedLoan = loan;
    this.selectedEmi = emi;

    // RESET FIELDS

    this.card1 = '';
    this.card2 = '';
    this.card3 = '';
    this.card4 = '';

    this.cardName = '';
    this.expiry = '';
    this.cvv = '';

    this.showPaymentPopup = true;

  }

  closePopup(): void {

    this.showPaymentPopup = false;

  }

  formatExpiry(): void {

    if (

      this.expiry.length === 2 &&

      !this.expiry.includes('/')

    ) {

      this.expiry =
        this.expiry + '/';

    }

  }

  confirmPayment(): void {

    if (

      this.card1.length !== 4 ||
      this.card2.length !== 4 ||
      this.card3.length !== 4 ||
      this.card4.length !== 4 ||

      !this.cardName ||

      this.expiry.length !== 5 ||

      this.cvv.length !== 3

    ) {

      alert(
        'Please fill valid card details'
      );

      return;

    }

    // PAYMENT SUCCESS

    this.selectedEmi.status = 'PAID';

    this.selectedLoan.remainingBalance =
      this.selectedLoan.remainingBalance -
      this.selectedEmi.amount;

    this.showPaymentPopup = false;

    alert(
      'EMI Payment Successful!'
    );

  }

}