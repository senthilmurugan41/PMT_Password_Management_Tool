import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSmartModalService } from 'ngx-smart-modal';
import { Group } from 'src/app/common/group';
import { GroupService } from 'src/app/services/group.service';
import { NavbarService } from 'src/app/services/navbar.service';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {

  groups: Group[];
  group: Group;
  groupName;
  groupId;
  constructor(public nav:NavbarService, private location:Location,
    private groupService: GroupService, public ngxModalService: NgxSmartModalService, private router: Router) { }


groupAdd=new FormGroup(
  {
    groupName:new FormControl('',[Validators.required,Validators.pattern('^[A-Za-z]+$')])
  }
)

get groupNames(){return this.groupAdd.get('groupName')}

  ngOnInit(): void {
    if (sessionStorage.length == 0)
      this.router.navigate(['login']);
    this.nav.show();
    this.getGroup();
  }
  getGroup() {
    this.groupService.getGroupList().subscribe(
      data => {
        this.groups = data;
      }
    )
  }
  openModal(val) {
    this.ngxModalService.getModal(val).open();
  }
  closeModal(val) {
    this.ngxModalService.getModal(val).close();
  }
  addGroupData(data) {
    this.closeModal('addGroup');
    this.group = data;
    this.groupService.addGroupData(this.group).subscribe(
      data => {
        this.group = data
        this.getGroup();
      }
    );

  }
  updateData(groupName, groupId) {
    this.groupName = groupName;
    this.groupId = groupId;
    this.openModal('updateGroup');
  }
  deleteGroup(group) {
    this.groupService.deleteGroup(group.groupName).subscribe(
      data => {
        this.group = data;
        this.getGroup();
      }
    );

  }
  updateGroupData(data) {
    this.closeModal('updateGroup');
    this.groupService.updateGroup(this.groupName, this.groupId).subscribe(
      data => {
        this.group = data
        this.getGroup();
      }
    );

  }
  back()
  {
    this.location.back();
  }
}
