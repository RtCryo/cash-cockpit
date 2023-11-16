import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../_model/User";
import {MessageDto} from "../_model/MessageDto";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  registration(user: User) {
    return this.http.post<MessageDto>(`${environment.hostUrl}/user/registration`, user, {withCredentials: true});
  }

}
