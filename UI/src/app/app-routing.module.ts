import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {FilesComponent} from './files/files.component';
import {RulesComponent} from './rules/rules.component';
import {TagsComponent} from './tags/tags.component';
import {TransactionsComponent} from './transactions/transactions.component';
import {UploadComponent} from './upload/upload.component';
import {VaultComponent} from './vault/vault.component';
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path: '**', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'transactions', component: TransactionsComponent},
  {path: 'tags', component: TagsComponent},
  {path: 'files', component: FilesComponent},
  {path: 'upload', component: UploadComponent},
  {path: 'rules', component: RulesComponent},
  {path: 'vault', component: VaultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
