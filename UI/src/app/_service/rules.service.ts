import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { MessageDto } from '../_model/MessageDto';
import { Rule } from '../_model/Rule';

@Injectable({
  providedIn: 'root'
})
export class RulesService {

  constructor(private http: HttpClient) { }

  getAllRules() {
    return this.http.get<Rule[]>(`${environment.hostUrl}/rule`, {withCredentials: true})
  }

  createRule(newRule:Rule){
    return this.http.post<MessageDto>(`${environment.hostUrl}/rule`, newRule, {withCredentials: true})
  }

  updateRule(rule:Rule){
    return this.http.post<MessageDto>(`${environment.hostUrl}/rule/` + rule.id, rule, {withCredentials: true})
  }
  
  deleteRules(ruleToDelete:string[]){
    return this.http.post<MessageDto>(`${environment.hostUrl}/rule/remove`, ruleToDelete, {withCredentials: true})
  }

  refreshAllTransactionsByRules(rules: string[]){
    return this.http.post<MessageDto>(`${environment.hostUrl}/rule/refresh`, rules, {withCredentials: true})
  }

}
