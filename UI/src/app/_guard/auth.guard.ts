import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from "../_service/authentication.service";


@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(
    private authenticationService: AuthenticationService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    if (currentUser && localStorage.getItem('fc_token') && localStorage.getItem('fc_refresh_token') && localStorage.getItem('currentUser')) {
      return true;
    }
    this.authenticationService.logout()
    return false;
  }
}
