import { Component } from '@angular/core';
import { Message, PrimeNGConfig } from 'primeng/api';
import {User} from "./_model/User";
import {AuthenticationService} from "./_service/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  msgs: Message[] = [];
  currentUser!: User;
  constructor(private primengConfig: PrimeNGConfig, private authenticationService: AuthenticationService) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

    ngOnInit() {
        this.primengConfig.ripple = true;
    }
}
