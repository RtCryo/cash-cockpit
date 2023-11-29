import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Consumer } from '../_model/Consumer';

@Injectable({
  providedIn: 'root'
})
export class ConsumerService {

  constructor(private http: HttpClient) { }

  getAllConsumers() {
    return this.http.get<Consumer[]>(`${environment.hostUrl}/consumer`, {withCredentials: true})
  }

}
