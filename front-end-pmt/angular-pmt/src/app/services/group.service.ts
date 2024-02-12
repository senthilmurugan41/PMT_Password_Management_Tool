import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from '../common/group';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  
  baseUrl="http://localhost:8000/readGroup";
  constructor(private httpClient:HttpClient) { }

  getGroupList():Observable<Group[]>
  {
    

    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.get<Group[]>(this.baseUrl,{headers});
  }

  addGroupData(group):Observable<Group>
  {
    

    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.post<Group>("http://localhost:8000/addGroup",group,{headers});
  }

  deleteGroup(groupName):Observable<Group> {


    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.delete<Group>("http://localhost:8000/deleteGroup/"+groupName,{headers});
  }
  updateGroup(groupName,id):Observable<Group>
  {


    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.put<Group>("http://localhost:8000/updateGroup/"+groupName+"/"+id,new Group(),{headers});
  }
  getGroupCount():Observable<any>
  {


    const headers= new HttpHeaders({Authorization:'Bearer '+sessionStorage.getItem('token')});
    return this.httpClient.get<any>("http://localhost:8000/getGroupCount",{headers});
  }
}
