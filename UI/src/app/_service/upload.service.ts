import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {MessageDto} from '../_model/MessageDto';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http: HttpClient) { }

  upload(files: File[]){
    let formData: FormData = new FormData();
    for(const element of files) {
      formData.append("files", element, element.name)
    }
    return this.http.post<MessageDto>(`${environment.hostUrl}/import`, formData);
 }

  getStatementList() {
      return this.http.get(`${environment.hostUrl}/statement`);
  }

  getFile(id: string) {
      return this.http.get(`${environment.hostUrl}/statement/` + id, {'responseType'  : 'arraybuffer' as 'json'});
  }

}
