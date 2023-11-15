import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ButtonModule } from 'primeng/button';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { NavbarComponent } from './navbar/navbar.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TagsComponent } from './tags/tags.component';
import { MegaMenuModule } from 'primeng/megamenu';
import { HeaderComponent } from './header/header.component';
import { ImageModule } from 'primeng/image';
import { FilesComponent } from './files/files.component';
import { UploadComponent } from './upload/upload.component';
import { ConsumersComponent } from './consumers/consumers.component';
import { TableModule } from 'primeng/table';
import { FileUploadModule } from 'primeng/fileupload';
import { HttpClientModule } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { RippleModule } from 'primeng/ripple';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { BlockUIModule } from 'primeng/blockui';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { PanelModule } from 'primeng/panel';
import { CalendarModule } from 'primeng/calendar';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { ToolbarModule } from 'primeng/toolbar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { DialogModule } from 'primeng/dialog';
import { StyleClassModule } from 'primeng/styleclass';
import { MultiSelectModule } from 'primeng/multiselect';
import { TagModule } from 'primeng/tag';
import { CheckboxModule } from 'primeng/checkbox';
import { TruncatePipe } from './_pipe/truncate.pipe';
import { RulesComponent } from './rules/rules.component';
import { SelectButtonModule } from 'primeng/selectbutton';
import { ChipsModule } from 'primeng/chips';
import { VaultComponent } from './vault/vault.component';
import { InputNumberModule } from 'primeng/inputnumber';
import { HomeComponent } from './home/home.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import {MenubarModule} from "primeng/menubar";
import {MenuModule} from "primeng/menu";
import {CardModule} from "primeng/card";
import {PasswordModule} from "primeng/password";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    TransactionsComponent,
    DashboardComponent,
    TagsComponent,
    HeaderComponent,
    FilesComponent,
    UploadComponent,
    ConsumersComponent,
    TruncatePipe,
    RulesComponent,
    VaultComponent,
    HomeComponent,
    UserDashboardComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        ButtonModule,
        TieredMenuModule,
        MegaMenuModule,
        ImageModule,
        TableModule,
        FileUploadModule,
        HttpClientModule,
        RippleModule,
        MessagesModule,
        MessageModule,
        BlockUIModule,
        ProgressSpinnerModule,
        PanelModule,
        CalendarModule,
        FormsModule,
        AutoCompleteModule,
        DynamicDialogModule,
        InputTextModule,
        InputTextareaModule,
        DropdownModule,
        ToolbarModule,
        ConfirmDialogModule,
        DialogModule,
        StyleClassModule,
        MultiSelectModule,
        TagModule,
        CheckboxModule,
        SelectButtonModule,
        ChipsModule,
        InputNumberModule,
        MenubarModule,
        MenuModule,
        ReactiveFormsModule,
        CardModule,
        PasswordModule
    ],
  providers: [MessageService, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
