import { Bebida } from './../../../model/bebida';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-bebida-list',
  templateUrl: './bebidalist.component.html',
  styleUrls: ['./bebidalist.component.scss']
})
export class BebidalistComponent {

  @Input() bebidas: Bebida[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() remove = new EventEmitter(false);
  readonly displayedColumns = ['nome', 'marca', 'litragem', 'sabor', 'preco', 'action'];

  constructor() {

  }

  onAdd() {
    this.add.emit(true);
  }
  onEdit(bebida: Bebida) {
    this.edit.emit(bebida);
  }
  onRemove(bebida: Bebida) {
    this.remove.emit(bebida);
  }

}
