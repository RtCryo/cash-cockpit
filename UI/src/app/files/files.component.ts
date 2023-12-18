import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {FileInfo} from '../_model/FileInfo';
import {TransactionService} from '../_service/transaction.service';
import {UploadService} from '../_service/upload.service';
import {saveAs as importedSaveAs} from "file-saver";
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent {
  fileInfoList!: FileInfo[];
  loading = false

  constructor(
    private uploadService: UploadService,
    private transactionService: TransactionService,
    private route: Router,
    private messageService: MessageService) {
    this.refreshFileList();
  }

  refreshFileList() {
    this.loading = true
    this.uploadService.getStatementList().subscribe({
      next: (event: any) => {
        this.fileInfoList = event;
        this.loading = false;
      },
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.loading = false;
      }
    })
  }

  getFile(fileInfo : FileInfo) {
    this.uploadService.getFile(fileInfo.bankStatementId).subscribe({
      next: (event:any) => {
        const blob = new Blob([event], {type: fileInfo.type});
        importedSaveAs(blob, fileInfo.name);
      }
    })
  }

  getTransactionsFromFile(fileInfo: FileInfo) {
    this.transactionService.setFileInfo(fileInfo);
    this.route.navigateByUrl("/transactions");
  }

}

