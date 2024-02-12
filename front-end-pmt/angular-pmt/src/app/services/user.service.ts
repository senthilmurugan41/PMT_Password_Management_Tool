import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  login(user)
  {
    // const headers= new HttpHeaders({Authorization:'Basic '+btoa(user.userName+':'+user.password)});
    // headers.append('Access-Control-Allow-Origin','*');
    // headers.append('Access-Control-Allow-Methods','GET, POST, PATCH, PUT, DELETE, OPTIONS');
    // headers.append('Access-Control-Allow-Headers',' Origin, Content-Type, X-Auth-Token');
    return this.httpClient.post("http://localhost:8000/authenticate",user,{responseType:'text' as 'json'});
  }
}
