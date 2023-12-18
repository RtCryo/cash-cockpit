import {Component} from '@angular/core';
import {MegaMenuItem} from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  items!: MegaMenuItem[];

  ngOnInit() {
    this.items = [
        { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: 'dashboard'},
        { label: 'Transactions', icon: 'pi pi-fw pi-euro', routerLink: 'transactions' },
        { label: 'Tags', icon: 'pi pi-fw pi-tags', routerLink: 'tags' },
        { label: 'Rules', icon: 'pi pi-fw pi-exclamation-triangle', routerLink: 'rules' },
        { label: 'Vault', icon: 'pi pi-fw pi-shield', routerLink: 'vault' },
        { label: 'Files', icon: 'pi pi-fw pi-save', routerLink: 'files' },
        { label: 'Import', icon: 'pi pi-fw pi-cloud-upload', routerLink: 'upload' },
    ];
  }
}
