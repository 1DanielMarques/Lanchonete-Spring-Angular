import { Bebida } from './../../../model/bebida';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-bebida-list',
  templateUrl: './bebidalist.component.html',
  styleUrls: ['./bebidalist.component.scss']
})
export class BebidalistComponent {

  @Input() bebidas: Bebida[] = [];
  @Output() add = new EventEmitter();
  readonly displayedColumns = ['nome', 'marca', 'litragem', 'sabor', 'preco', 'action'];

  constructor() {

  }

  onAdd() {
    this.add.emit(true);
  }

}
