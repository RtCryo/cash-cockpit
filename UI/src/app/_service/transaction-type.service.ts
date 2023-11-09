import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TransactionType } from '../_model/TransactionType';

@Injectable({
  providedIn: 'root'
})
export class TransactionTypeService {

  constructor(private http: HttpClient) { }

  getAllTypes() {
    return this.http.get<TransactionType[]>(`${environment.hostUrl}/types`, {withCredentials: true})
  }

}
