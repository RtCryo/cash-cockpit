import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { MessageDto } from '../_model/MessageDto';
import { UploadService } from '../_service/upload.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  uploadedFiles: any[] = [];
  blockedDocument: boolean = false;
  constructor(private messageService: MessageService, private uploadService: UploadService) {}

  onUpload(event: { files: any; }, form: any) {
    let tempFiles:File[] = []
    this.blockedDocument = true
    for(let file of event.files) {
        tempFiles.push(file);   
    }
    this.uploadService.upload(tempFiles).subscribe({
      next: (event: MessageDto) => {
        this.blockedDocument = false;
        setTimeout(() => {}, 500);
        this.messageService.add({severity: 'success', summary: 'File(s) Uploaded', detail: event.message, life: 3000});
        form.clear()
        for(let file of tempFiles) {
          this.uploadedFiles.push(file);
        }
      },
      error: (response) => {
        this.blockedDocument = false
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
      }
    })
  }

}
