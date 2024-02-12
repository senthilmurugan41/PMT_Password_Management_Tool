import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/common/user';
import { NavbarService } from 'src/app/services/navbar.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:User;
  invalidCred;

  constructor(private userService:UserService,private router:Router,public nav:NavbarService) { }

  ngOnInit(): void {
    
    this.nav.hide();
  }

  loginData(data)
  {
    this.user=data;
    let req= this.userService.login(this.user);
    req.subscribe(
      data=>{
          sessionStorage.setItem('token',data.toString());
          this.router.navigate(['/dashboard']);
      },
      error=>{
        this.invalidCred=true;
      }
    )
  }

}
