import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSmartModalService } from 'ngx-smart-modal';
import { Account } from 'src/app/common/account';
import { AccountService } from 'src/app/services/account.service';
import { NavbarService } from 'src/app/services/navbar.service';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {
  value=this.router.snapshot.paramMap.get('id');
  accountId;
  value1=this.router.snapshot.queryParamMap.has('value')
  accounts:Account[];
  account:Account=new Account();
  constructor(public nav:NavbarService,private location:Location,
    private route:Router,private router:ActivatedRoute,private accountService:AccountService,public ngxSmartService:NgxSmartModalService) {
   
   }
  

   accountAdd=new FormGroup({
     accountName: new FormControl('',[Validators.required,Validators.pattern('^[A-Za-z]+$')]),
     url: new FormControl('',[Validators.required,Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')]),
     password:new FormControl('',Validators.required),
     groupName:new FormControl(this.value,[Validators.required,Validators.pattern('^[A-Za-z]+$')])
   });

   get url(){return this.accountAdd.get('url')}
   get accountName(){return this.accountAdd.get('accountName')}
   get password(){return this.accountAdd.get('password')}
   get groupName(){return this.accountAdd.get('groupName')}

  ngOnInit(): void {
    console.log(`this`,this.value)
    if(sessionStorage.length==0)
      this.route.navigate(['login']);
    this.nav.show();
    if(this.value1)
    {
      this.getAccountLists();
    }
    else
    this.getAccountList();

    console.log(this.value1);
    
  }
  getAccountLists()
  {
    this.accountService.getAccountLists().subscribe(
      data=>{
        this.accounts=data;
      }
      )
  }
  getAccountList()
  {
    this.accountService.getAccountList(this.value).subscribe(
      data=>{
        this.accounts=data;
      }
    )
  }
  openModal(value)
  {
    this.ngxSmartService.getModal(value).open();
  }
  closeModal(value)
  {
    this.ngxSmartService.getModal(value).close();
  }
  addAccountData(data)
  {
    this.closeModal('addAccount');
    this.account=data.value;
    data.reset;
    this.accountService.addAccount(this.account).subscribe(
      data=>{
        this.account=data;
        if(this.value1)
          this.getAccountLists();
        else
        this.getAccountList();
      }
    )
  }
  deleteData(data)
  {
    this.accountService.deleteAccount(data).subscribe(
      data=>{
        this.account=data;
        if(this.value1)
          this.getAccountLists();
        else
          this.getAccountList();
      }
    )
  }
  back()
  {
    this.location.back();
  }
  updateData(valueAccount)
  {
    this.account=valueAccount;
    this.accountId=this.account.id;
    this.openModal('updateAccount');

  }
  updateAccountData(data)
  {
    this.closeModal('updateAccount');
    this.account=data.value;
    this.accountService.updateAccount(this.account,this.accountId).subscribe(
      data=>{
        this.account=data;
        if(this.value1)
          this.getAccountLists();
        else
          this.getAccountList();
      }
    )
  }
}
