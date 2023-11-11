import { Component } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Rule } from '../_model/Rule';
import { Tag } from '../_model/Tag';
import { RulesService } from '../_service/rules.service';
import { TagService } from '../_service/tag.service';

@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css'],
})
export class RulesComponent {

  tags: Tag[] = [];
  rules: Rule[] = [];
  updateRule:Rule = {id: "", tagId: "", area: "INFO", has: []};
  selectedRules!: Rule[];
  loading = false;
  create = false;
  update = false;
  blockedDocument = false;
  separator: string = " ";
  newRule: Rule = {id: "", tagId: "", area: "INFO", has: []};
  refresh = false;
  options: any[] = [
    {label: "Consumer", value: "CONSUMER"},
    {label: "Sender", value: "SENDER"},
    {label: "Info", value: "INFO"},
    {label: "Type", value: "TYPE"}];

  constructor(
    private messageService: MessageService, 
    private rulesService: RulesService, 
    private confirmationService: ConfirmationService,
    private tagService: TagService) {
    this.loading = true;
    this.refreshPageRules();
    this.selectedRules = [];
    tagService.getAllTags().subscribe({
      next: (response) => {
        this.tags = response;
        this.blockedDocument = false;
        this.loading = false;
      },
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.loading = false;
      }
    })
  }

  refreshPageRules() {
    this.rulesService.getAllRules().subscribe({
      next: (response) => {
        this.rules = response;
        this.blockedDocument = false;
        this.create = false;
        this.update = false;
        this.loading = false;
      }, 
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.loading = false;
      }
    })
  }

  openNew() {
    this.newRule = {id: "", tagId: "", area: "INFO", has: []};
    this.refresh = true;
    this.create = true;
  }

  confirmDelete(){
    this.confirmationService.confirm({
      message: 'Are you sure that you want to proceed?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.blockedDocument = true;
        let t:string[] = [];
        this.selectedRules.forEach(element => {
          t.push(element.id);
        });
        this.rulesService.deleteRules(t).subscribe({
          next: () => {
            this.refreshPageRules()
          }, 
          error: () => {
            this.blockedDocument = false;
          }
        })
      },
      reject: () => {
        this.messageService.add({severity:'warn', summary:'Cancelled', detail:'You have cancelled'});
      },
      key: "positionDialog"
    });
  }

  refreshSelectedRules(){
    this.blockedDocument = true;
    let ids:string[] = [];
    this.rules.forEach(el => {
      ids.push(el.id)
    })
    this.rulesService.refreshAllTransactionsByRules(ids).subscribe({
      next: (response) =>{
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'success', summary: 'Success', detail: response.message, life: 3000})
        this.blockedDocument = false;
        this.create = false;
      }, 
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.message, life: 3000})
        this.blockedDocument = false;
        this.create = false;
      }
    })
  }

  refreshRule(rule: Rule){
    this.blockedDocument = true;
    this.rulesService.refreshAllTransactionsByRules([rule.id]).subscribe({
      next: (response) =>{
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'success', summary: 'Success', detail: response.message, life: 3000})
        this.blockedDocument = false;
        this.create = false;
      }, 
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.message, life: 3000})
        this.blockedDocument = false;
        this.create = false;
      }
    })
  }

  createRule(){
    if (this.newRule != null && this.newRule.id == "" && this.newRule.area.length > 0 && this.newRule.tagId.length > 0) {
      this.blockedDocument = true;
      this.rulesService.createRule(this.newRule).subscribe({
        next: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'success', summary: 'Success', detail: response.message, life: 3000})
          this.refreshPageRules();
        }, 
        error: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
          this.blockedDocument = false;
          this.create = false;
        }
      })
    } else {
      this.create = false;
    }
  }

  openUpdate(rule: Rule) {
    this.updateRule = rule;
    this.refresh = true;
    this.update = true;
  }

  sendUpdateRule(){
    if (this.updateRule != null && this.updateRule.area.length > 0 && this.updateRule.id.length > 0 && this.updateRule.tagId.length > 0 && this.updateRule.has.length > 0) {
      this.blockedDocument = true;
      this.rulesService.updateRule(this.updateRule).subscribe({
        next: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'success', summary: 'Success', detail: response.message, life: 3000})
          this.refreshPageRules();
        }, 
        error: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'error', summary: 'Error', detail: response.message, life: 3000})
          this.blockedDocument = false;
          this.update = false;
        }
      })
    } else {
      this.update = false;
    }
  }

  getTagNameById(tagId: string) {
    let result = "no name";
    this.tags.forEach((tag: Tag) => {
      if (tag.id === tagId) {
        result = tag.tagName;
        return;
      }
    })
    return result;
  }
}
