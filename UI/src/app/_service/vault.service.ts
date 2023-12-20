import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {MessageDto} from '../_model/MessageDto';
import {Vault} from '../_model/Vault';

@Injectable({
  providedIn: 'root'
})
export class VaultService {

  constructor(private http: HttpClient) { }

  getAllVault() {
    return this.http.get<Vault[]>(`${environment.hostUrl}/vault`, {withCredentials: true})
  }

  saveVault(amount: number) {
    return this.http.post<MessageDto>(`${environment.hostUrl}/vault`, amount, {withCredentials: true})
  }

  deleteVault(id:string) {
    return this.http.delete<MessageDto>(`${environment.hostUrl}/vault/${id}`, {withCredentials: true})
  }

}
