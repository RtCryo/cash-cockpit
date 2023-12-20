import {Component, ViewChild} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';
import {DialogService} from 'primeng/dynamicdialog';
import {Table} from 'primeng/table';
import {MessageDto} from '../_model/MessageDto';
import {Vault} from '../_model/Vault';
import {VaultService} from '../_service/vault.service';

@Component({
  selector: 'app-vault',
  templateUrl: './vault.component.html',
  styleUrls: ['./vault.component.css'],
  providers: [DialogService]
})
export class VaultComponent {

  @ViewChild('dt') table!: Table;

  vault: Vault[] = [];
  loading: boolean = true;
  blockedDocument: boolean = true;
  create: boolean = false;
  newAmount: number = 0;

  constructor(
    private vaultService: VaultService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) {
    this.loading = true;
    this.blockedDocument = true;
    vaultService.getAllVault().subscribe({
      next: (response) => {
        this.vault = response;
      },
      error: (response) => {
        this.loading = false
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
      }
    })
    this.loading = false;
    this.blockedDocument = false;
  }

  summ(){
    let t: number = 0;
    this.vault.forEach(element => {
      t += +element.sum
    });
    return this.roundToDigit(t,2);
  }

  roundToDigit(num:number, digits:number){
    return Math.round(num * Math.pow(10, digits) + Number.EPSILON) / Math.pow(10, digits)
  };

  addNewVault(){
    this.create = true;
    this.newAmount = 0;
  }

  saveVault(){
    if (this.newAmount > 0) {
      this.blockedDocument = true;
      this.vaultService.saveVault(this.newAmount).subscribe({
        next: (response) => {
          setTimeout(() => {}, 500)
          this.messageService.add({severity: 'success', summary: 'Success', detail: response.message, life: 3000})
          this.refreshPageVault();
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

  refreshPageVault() {
    this.vaultService.getAllVault().subscribe({
      next: (response) => {
        this.vault = response;
        this.blockedDocument = false;
        this.create = false;
        this.loading = false;
      },
      error: (response) => {
        setTimeout(() => {}, 500)
        this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
        this.loading = false;
      }
    })
  }

  deleteVault(vault: Vault) {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to proceed?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.blockedDocument = true;
        this.vaultService.deleteVault(vault.id).subscribe({
          next: (msg: MessageDto) => {
            setTimeout(() => {}, 500)
            this.messageService.add({severity: 'success', summary: 'Success', detail: msg.message, life: 3000})
            this.refreshPageVault();
          },
          error: (response) => {
            setTimeout(() => {}, 500)
            this.messageService.add({severity: 'error', summary: 'Error', detail: response.error.message, life: 3000})
            this.blockedDocument = false;
            this.create = false;
            this.loading = false;
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
