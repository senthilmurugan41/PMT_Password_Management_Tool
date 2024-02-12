import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { NavbarService } from 'src/app/services/navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private location:Location,public nav:NavbarService,private router:Router) {
   }
  ngOnInit(): void {
      
  }
  logout()
  {
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

}
