import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Endereco } from 'src/app/lanchonete/model/endereco';
import { Lanche } from 'src/app/lanchonete/model/lanche';
import { Pedido } from 'src/app/lanchonete/model/pedido';

@Component({
  selector: 'app-pedidos-list',
  templateUrl: './pedidos-list.component.html',
  styleUrls: ['./pedidos-list.component.scss']
})
export class PedidosListComponent implements OnInit {
  constructor() {
    this.pedidos.forEach(pedido => {
      this.totalAux = +pedido.total;
      pedido.total = this.totalAux.toFixed(2);
    });
  }
  ngOnInit(): void {
    this.pedidos.forEach(pedido => {
      this.totalAux = +pedido.total;
      pedido.total = this.totalAux.toFixed(2);
    });
  }
  totalAux: number = 0;
  @Input() pedidos: Pedido[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  @Output() dialog = new EventEmitter(false);

  readonly displayedColumns = ['id', 'lanches', 'qtdLanches', 'bebidas', 'qtdBebidas', 'endereco', 'pagamento', 'taxa', 'total', 'action'];
  panelOpenState = false;

  verificaVazioLanche(items: string[]) {
    if (items.length > 0) {
      return true;
    }
    return false;
  }

  verificaVazioBebida(items: string[]) {
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

  onEndereco(endereco: Endereco) {
    this.dialog.emit(endereco);
  }

  verificaDecimal(item: any) {
    let num: number = 0;
    num = +item.preco;
    item.preco = num.toFixed(2);
    return true;
  }



}

