import { Component } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Tag } from '../_model/Tag';
import { TagService } from '../_service/tag.service';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css'],
  providers: [ConfirmationService]
})
export class TagsComponent {

  tags: Tag[] = [];
  updateTag!:Tag;
  selectedTags: Tag[];
  loading = false;
  create = false;
  update = false;
  blockedDocument = false;
  newTag: string;

  constructor(
    private messageService: MessageService, 
    private tagsService: TagService, 
    private confirmationService: ConfirmationService) {
    this.loading = true;
    this.refreshPageTags();
    this.selectedTags = [];
    this.newTag = "";
  }

  openNew() {
    this.create = true;
  }

  openUpdate(tag: Tag) {
    this.updateTag = tag;
    this.newTag = tag.tagName;
    this.update = true;
  }

  refreshPageTags() {
    this.tagsService.getAllTags().subscribe({
      next: (response) => {
        this.tags = response;
        this.blockedDocument = false;
        this.create = false;
        this.newTag = "";
        this.loading = false;
      }, 
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.loading = false;
      }
    })
  }

  createTag(){
    if (this.newTag != null && this.newTag.length > 0) {
      this.blockedDocument = true;
      this.tagsService.createTag(this.newTag).subscribe({
        next: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'success', summary: 'Success', detail: response.message, life: 3000})
          this.refreshPageTags();
        }, 
        error: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'error', summary: 'Error', detail: response.message, life: 3000})
          this.blockedDocument = false;
          this.create = false;
        }
      })
    } else {
      this.create = false;
    }
  }

  sendUpdateTag(){
    if (this.newTag != null && this.newTag.length > 0) {
      this.blockedDocument = true;
      this.updateTag.tagName = this.newTag;
      this.tagsService.updateTag(this.updateTag).subscribe({
        next: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'success', summary: 'Success', detail: response.message, life: 3000})
          this.refreshPageTags();
        }, 
        error: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'error', summary: 'Error', detail: response.message, life: 3000})
          this.blockedDocument = false;
          this.create = false;
        }
      })
    } else {
      this.create = false;
    }
  }

  confirmDelete(){
    this.confirmationService.confirm({
      message: 'Are you sure that you want to proceed?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.blockedDocument = true;
        this.tagsService.deleteTags(this.selectedTags).subscribe({
          next: () => {
            this.refreshPageTags()
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

}
