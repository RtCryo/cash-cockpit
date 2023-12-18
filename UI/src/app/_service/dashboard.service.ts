import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {ReportDto} from '../_model/ReportDto';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }

  getReport() {
    return this.http.get<ReportDto>(`${environment.hostUrl}/report`, {withCredentials: true})
  }

}
