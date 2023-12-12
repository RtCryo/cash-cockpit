import {Injectable} from '@angular/core';
import {interval, Subscription, switchMap} from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {MessageService} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class PollingMessageService {
  private apiUrl = environment.hostUrl;
  private pollInterval = 3000;
  private pollSubscription!: Subscription;

  constructor(private http: HttpClient, private messageService: MessageService) {
  }

  startPolling() {
    this.pollSubscription = interval(this.pollInterval).pipe(
      switchMap(() => this.http.get(this.apiUrl))
    ).subscribe({
      next: (response) => {
        this.messageService.add({severity: 'error', summary: 'Error', detail: "Can't load report", life: 3000})
      },
      error: (response) => {
        console.error('Error fetching data:', response);
      }
    })
  }

  stopPolling() {
    if (this.pollSubscription) {
      this.pollSubscription.unsubscribe();
    }
  }
}
