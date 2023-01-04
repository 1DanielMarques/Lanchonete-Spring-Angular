import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Bebida } from 'src/app/lanchonete/model/bebida';

@Component({
  selector: 'app-bebidas-list',
  templateUrl: './bebidas-list.component.html',
  styleUrls: ['./bebidas-list.component.scss']
})
export class BebidasListComponent {

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
  verificaDecimal(bebidas: Bebida[]) {
    let num: number = 0;
    bebidas.forEach(bebida => {
      num = +bebida.preco;
        bebida.preco = num.toFixed(2);
    });
    return true;
  }

}
