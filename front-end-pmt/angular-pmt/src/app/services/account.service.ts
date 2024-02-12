import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../common/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
 

  constructor(private httpClient:HttpClient) { }

  getAccountLists():Observable<Account[]>
  {
    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});

    return this.httpClient.get<Account[]>("http://localhost:8000/getAllAccount",{headers});
  }

  getAccountList(value):Observable<Account[]>
  {
    
    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.get<Account[]>("http://localhost:8000/displayAccount/"+value,{headers});
  }

  addAccount(account):Observable<Account>
  {


    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.post<Account>("http://localhost:8000/addAccount",account,{headers});
  }
  deleteAccount(data):Observable<Account> {


    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.delete<Account>("http://localhost:8000/deleteAccount/"+data,{headers});
  }
  getAccountCount():Observable<any>
  {


    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
      return this.httpClient.get<any>("http://localhost:8000/getAccountCount",{headers});
  }
  updateAccount(account,id):Observable<Account>
  {
 

    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.put<Account>("http://localhost:8000/updateAccount/"+id,account,{headers});
  }

}
