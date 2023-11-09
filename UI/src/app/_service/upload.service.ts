import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MessageDto } from '../_model/MessageDto';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http: HttpClient) { }

  upload(files: File[]){
    const formData: FormData = new FormData();
    for(let t = 0; t < files.length; t++) {
      formData.append("files", files[t], files[t].name)
    }
    return this.http.post<MessageDto>(`${environment.hostUrl}/import`, formData);
 }

  getStatemenList() {
      return this.http.get(`${environment.hostUrl}/statement`);
  }

  getFile(id: string) {
      return this.http.get(`${environment.hostUrl}/statement/` + id, {'responseType'  : 'arraybuffer' as 'json'});
  }

}
