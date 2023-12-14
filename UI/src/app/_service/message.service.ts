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
      switchMap(() => this.http.get<string[]>(this.apiUrl + "/message"))
    ).subscribe({
      next: (response: string[]) => {
        if (response.length == 0) return
        response.forEach(value => {
          this.messageService.add({severity: 'info', summary: 'Info', detail: value, life: 3000})
          this.delay(4000);
        })
      },
      error: (response) => {
        console.error('Error message service:', response);
      }
    })
  }

  stopPolling() {
    if (this.pollSubscription) {
      this.pollSubscription.unsubscribe();
    }
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }
}
