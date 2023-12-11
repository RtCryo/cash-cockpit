import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {AuthenticationService} from "../_service/authentication.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {


  constructor(private router: Router, private authService: AuthenticationService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (localStorage.getItem('fc_token') === null || localStorage.getItem('fc_currentUser') === null) {
      this.router.navigateByUrl("/home");
    }

    req = req.clone({
      setHeaders: {
        'Accept': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('fc_token')}`,
      },
    });

    return next.handle(req).pipe(
      map((event: HttpEvent<any>) => {
        return event;
      }),
      catchError((err: HttpErrorResponse, _: Observable<any>) => {
        if ([400, 401, 403].indexOf(err.status) !== -1) {
          this.authService.logout();
        }

        return throwError(() => err);
      })
    );
  }

}
