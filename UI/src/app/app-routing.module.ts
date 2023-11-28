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
import {AuthGuard} from "./_guard/auth.guard";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'transactions', component: TransactionsComponent, canActivate: [AuthGuard]},
  {path: 'tags', component: TagsComponent, canActivate: [AuthGuard]},
  {path: 'files', component: FilesComponent, canActivate: [AuthGuard]},
  {path: 'upload', component: UploadComponent, canActivate: [AuthGuard]},
  {path: 'rules', component: RulesComponent, canActivate: [AuthGuard]},
  {path: 'vault', component: VaultComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


//authGuard
