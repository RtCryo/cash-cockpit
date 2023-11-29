import {Injectable} from '@angular/core';
import {User} from "../_model/User";
import {BehaviorSubject, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Router} from "@angular/router";
import {AuthResponse} from "../_model/AuthResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    try {
      this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('fc_currentUser')!));
      this.currentUser = this.currentUserSubject.asObservable();
    } catch (error: any){
      localStorage.removeItem('fc_currentUser');
      this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('fc_currentUser')!));
      this.currentUser = this.currentUserSubject.asObservable();
    }
  }

  public get currentUserValue(): User {
    return this.currentUserSubject?.value;
  }


  login(user: User) {
    return this.http.post<AuthResponse>(`${environment.hostUrl}/auth/login`, user)
      .pipe(map(authResponse => {
        localStorage.setItem('fc_token', authResponse.accessToken);
        localStorage.setItem('fc_refresh_token', authResponse.refreshToken);
        localStorage.setItem('fc_currentUser', JSON.stringify(new User(authResponse.username, "")));
        this.currentUserSubject.next(new User(authResponse.username, ""));
        return authResponse;
      }));
  }

  registration(user: User): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${environment.hostUrl}/auth/register`, user)
      .pipe(map(authResponse => {
        localStorage.setItem('fc_token', authResponse.accessToken);
        localStorage.setItem('fc_refresh_token', authResponse.refreshToken);
        localStorage.setItem('fc_currentUser', JSON.stringify(new User(authResponse.username, "")));
        this.currentUserSubject.next(new User(authResponse.username, ""));
        return authResponse;
      }));
  }

  logout() {
    localStorage.removeItem('fc_token');
    localStorage.removeItem('fc_refresh_token');
    localStorage.removeItem('fc_currentUser');
    this.currentUserSubject.next(null!);
    this.router.navigate(['/home']);
  }
}
