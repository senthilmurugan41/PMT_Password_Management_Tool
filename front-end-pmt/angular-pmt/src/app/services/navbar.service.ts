import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {
  visible=false;
  constructor() { }
  hide() { this.visible = false; }

  show() { this.visible = true; }
}
