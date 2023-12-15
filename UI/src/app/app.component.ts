import {Component, OnInit} from '@angular/core';
import {Message, PrimeNGConfig} from 'primeng/api';
import {User} from "./_model/User";
import {AuthenticationService} from "./_service/authentication.service";
import {PollingMessageService} from "./_service/message.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  msgs: Message[] = [];
  currentUser!: User;

  constructor(private primengConfig: PrimeNGConfig, private authenticationService: AuthenticationService, private messageService: PollingMessageService) {
  }

  ngOnInit() {
    this.primengConfig.ripple = true;
    this.authenticationService.currentUser.subscribe(x => {
      this.currentUser = x
      if (this.currentUser !== null) this.messageService.startPolling();
    });
  }

  ngOnDestroy() {
    this.messageService.stopPolling();
  }
}
