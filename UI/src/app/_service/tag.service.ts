import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MessageDto } from '../_model/MessageDto';
import { Tag } from '../_model/Tag';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) { }

  getAllTags() {
    return this.http.get<Tag[]>(`${environment.hostUrl}/tag`, {withCredentials: true})
  }

  refreshAllTransactionsByCategory(tags: Tag[]){
    return this.http.post<MessageDto>(`${environment.hostUrl}/tag/refresh`, tags, {withCredentials: true})
  }

  createTag(newTagName:string){
    return this.http.post<MessageDto>(`${environment.hostUrl}/tag`, newTagName, {withCredentials: true})
  }

  updateTag(tag:Tag){
    return this.http.post<MessageDto>(`${environment.hostUrl}/tag/` + tag.id, tag, {withCredentials: true})
  }
  
  deleteTags(tagToDelete:Tag[]){
    return this.http.post<MessageDto>(`${environment.hostUrl}/tag/remove`, tagToDelete, {withCredentials: true})
  }
  
}
