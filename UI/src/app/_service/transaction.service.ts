import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { FilterDto } from '../_model/FilterDto';
import { FileInfo } from '../_model/FileInfo';
import { Transaction } from '../_model/Transaction';
import { MessageDto } from '../_model/MessageDto';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  fileInfo!: FileInfo;

  constructor(private http: HttpClient) { }

  getTransactionsWithFilter(date: FilterDto) {
    return this.http.post<Transaction[]>(`${environment.hostUrl}/transaction/search`, date, {withCredentials: true})
  }

  getTransaction(id:number) {
    return this.http.get<Transaction>(`${environment.hostUrl}/transaction/${id}`, {withCredentials: true})
  }

  deleteTransaction(id:string) {
    return this.http.post<MessageDto>(`${environment.hostUrl}/transaction/delete/${id}`, {withCredentials: true})
  }

  updateTransaction(transaction: Transaction) {
    return this.http.put<void>(`${environment.hostUrl}/transaction/${transaction.id}`, transaction, {withCredentials: true})
  }

  saveCustomeTransaction(transaction: Transaction) {
    return this.http.post<void>(`${environment.hostUrl}/transaction`, transaction, {withCredentials: true})
  }

  getFileInfo(){
    return this.fileInfo;
  }

  setFileInfo(data: FileInfo){
    this.fileInfo = data;
  }
  
}
