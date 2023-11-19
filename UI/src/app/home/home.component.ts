import {Component} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../_service/authentication.service";
import {MessageService} from "primeng/api";
import {UserService} from "../_service/user.service";
import {User} from "../_model/User";
import {repeat} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  formGroup!: FormGroup;
  blockedDocument: boolean = false;
  registration: boolean = false;
  value!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private messageService: MessageService,
    private userService: UserService
  ) {
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.formGroup = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.pattern(
        '[A-Za-z0-9]*'
      ), Validators.minLength(3), Validators.maxLength(11)]),
      password: new FormControl('', [Validators.required, Validators.pattern(
        '[A-Za-z0-9]*'
      ), Validators.minLength(3), Validators.maxLength(11)])
    });
  }

  registrationChange() {
    this.registration = !this.registration;
    if (this.registration) {
      this.formGroup.addControl("repeat", new FormControl('', [Validators.required, Validators.pattern(
        '[A-Za-z0-9]*'
      ), Validators.minLength(3), Validators.maxLength(11)]));
      this.formGroup.addValidators(this.confirmPasswordValidator)
    } else {
      this.formGroup.setErrors(null)
      this.formGroup.removeControl('repeat')
      this.formGroup.removeValidators(this.confirmPasswordValidator)
    }
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
          setTimeout(() => {
          }, 500)
          this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        }
      });
  }

  onSubmitRegistration() {
    if (this.formGroup.invalid) {
      if (this.formGroup.hasError("PasswordNoMatch")) {
        this.messageService.add({severity: 'error', summary: 'Error', detail: "Password is't the same", life: 3000})
      }
      return;
    }
    this.blockedDocument = true
    const newUser: User = {
      username: this.formGroup.controls['username'].value,
      password: this.formGroup.controls['password'].value
    }
    this.userService.registration(newUser).subscribe({
      next: (msg) => {
        this.blockedDocument = false;
        this.messageService.add({severity: 'success', summary: 'Success', detail: msg.message, life: 3000})
        this.router.navigate(["/dashboard"]);
      },
      error: (response) => {
        this.blockedDocument = false
        setTimeout(() => {
        }, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
      }
    })
  }

  confirmPasswordValidator: ValidatorFn = (
    control: AbstractControl
  ): ValidationErrors | null => {
    return control.value.password === control.value.repeat
      ? null
      : {PasswordNoMatch: true};
  };

  protected readonly repeat = repeat;
}
