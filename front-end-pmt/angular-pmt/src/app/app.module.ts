import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GroupListComponent } from './components/group-list/group-list.component';
import {HttpClientModule} from '@angular/common/http'
import { GroupService } from './services/group.service';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgxSmartModalModule, NgxSmartModalService } from 'ngx-smart-modal';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AccountListComponent } from './components/account-list/account-list.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    GroupListComponent,
    AccountListComponent,
    LoginComponent,
    NavbarComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxDatatableModule,
    FontAwesomeModule,
    NgxSmartModalModule.forChild(),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [GroupService,NgxSmartModalService],
  bootstrap: [AppComponent]
})
export class AppModule { }
