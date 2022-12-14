import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Lanche } from 'src/app/lanchonete/model/lanche';

@Component({
  selector: 'app-lanches-list',
  templateUrl: './lanches-list.component.html',
  styleUrls: ['./lanches-list.component.scss']
})
export class LanchesListComponent {

  @Input() lanches: Lanche[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);

  readonly displayedColumns = ['nome', 'preco', 'descricao', 'action'];

  constructor() {

  }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(lanche: Lanche) {
    this.edit.emit(lanche);
  }


}
