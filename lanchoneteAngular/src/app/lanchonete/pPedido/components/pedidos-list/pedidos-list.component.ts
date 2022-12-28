import { Component, Input, EventEmitter, Output } from '@angular/core';
import { Pedido } from 'src/app/lanchonete/model/pedido';

@Component({
  selector: 'app-pedidos-list',
  templateUrl: './pedidos-list.component.html',
  styleUrls: ['./pedidos-list.component.scss']
})
export class PedidosListComponent {
  constructor() {

  }

  @Input() pedidos: Pedido[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);

  readonly displayedColumns = ['id', 'lanches', 'qtdLanches', 'bebidas', 'qtdBebidas','endereco', 'pagamento', 'taxa', 'total', 'action'];
  panelOpenState = false;

  verificaVazio(items: string[]) {
    if (items.length > 0) {
      return true;
    }
    return false;
  }

  onAdd() {
    this.add.emit(true);
  }
  onEdit(pedido: Pedido) {
    this.edit.emit(pedido);
  }
  onRemove(pedido: Pedido) {
    this.delete.emit(pedido);
  }

  openDialog(){

  }


}
