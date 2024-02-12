import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountListComponent } from './components/account-list/account-list.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { GroupListComponent } from './components/group-list/group-list.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  {path:'dashboard',component:DashboardComponent},
  {path:'account-list',component:AccountListComponent},
  {path:'account-list/:id',component:AccountListComponent},
  {path:'group-list',component:GroupListComponent},
  {path:'login',component:LoginComponent},
  {path:'',redirectTo:'login',pathMatch: 'full'},
  {path:'**',redirectTo:'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
