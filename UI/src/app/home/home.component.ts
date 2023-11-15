import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../_service/authentication.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  formGroup!: FormGroup;
  blockedDocument: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private messageService: MessageService
  ) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.formGroup = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
  }

  onSubmit() {
    if (this.formGroup.invalid) {
      return;
    }
    this.blockedDocument = true
    this.authenticationService.login(this.formGroup.value['username'], this.formGroup.value['password'])
      .subscribe({
        next: () => {
          this.blockedDocument = false
          const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigate([returnUrl]);
        },
        error: (response) => {
          this.blockedDocument = false
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        }
      });
  }
}
