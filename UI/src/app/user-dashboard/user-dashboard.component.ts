import {Component} from '@angular/core';
import {User} from "../_model/User";
import {AuthenticationService} from "../_service/authentication.service";
import {Router} from "@angular/router";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent {

  imgLink: string = "/assets/images/user.svg";
  currentUser!: User;
  items: MenuItem[];

  constructor(private router: Router, private authenticationService: AuthenticationService) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    this.items = [
      {
        label: 'Logout',
        icon: 'pi pi-fw pi-power-off',
        visible: this.currentUser != null,
        command: event => this.logout()
      }
    ]
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/home']);
  }
}
