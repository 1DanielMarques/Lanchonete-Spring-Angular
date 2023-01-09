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
  @Output() remove = new EventEmitter(false);

  readonly displayedColumns = ['nome', 'descricao', 'preco', 'action'];

  constructor() {

  }

  onAdd() {
    this.add.emit(true);
  }

  onEdit(lanche: Lanche) {
    this.edit.emit(lanche);
  }

  onRemove(lanche: Lanche) {
    this.remove.emit(lanche);
  }

  verificaDecimal(lanches: Lanche[]) {
    let num: number = 0;
    lanches.forEach(lanche => {
      num = +lanche.preco;
      lanche.preco = num.toFixed(2);
    });
    return true;
  }


}
