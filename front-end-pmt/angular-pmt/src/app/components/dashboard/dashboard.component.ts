import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LongPressDirective } from '@swimlane/ngx-datatable';
import { AccountService } from 'src/app/services/account.service';
import { GroupService } from 'src/app/services/group.service';
import { NavbarService } from 'src/app/services/navbar.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private route:Router,private nav:NavbarService,private accountService:AccountService,private groupService:GroupService) { }

  accountCount;
  groupCount;

  ngOnInit(): void {
    if(sessionStorage.length==0)
      this.route.navigate(['login']);
    this.nav.show();
    
    this.getAccountCount();
    this.getGroupCount();
  }
  getAccountCount()
  {
    this.accountService.getAccountCount().subscribe(
      data=>{
        this.accountCount=data;
      }
    )
  }

  getGroupCount()
  {
    this.groupService.getGroupCount().subscribe(
      data=>{
        this.groupCount=data;
      }
    )
  }

}
