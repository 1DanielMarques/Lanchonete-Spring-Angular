import { catchError, Observable, of } from 'rxjs';
import { BebidaService } from './../../../../services/bebida/bebida.service';
import { LancheService } from './../../../../services/lanche/lanche.service';
import { Component } from '@angular/core';
import { Pedido } from 'src/app/lanchonete/model/pedido';
import { Lanche } from 'src/app/lanchonete/model/lanche';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.scss']
})
export class PedidosComponent {

  pedidos$: Pedido[] = [
    {
      id: '1',
      lanches: [{id:'',nome:'X-Frango',preco:'',descricao:''},{id:'',nome:'X-Bacon',preco:'',descricao:''}],
      qtdLanches: '1',
      bebidas: this.bebidaService.findAll(),
      qtdBebidas: '2',
      taxa: '12.55',
      pagamento: 'DEBITO',
      total: '22.59'
    }
  ];

  readonly displayedColumnsLanche = ['id', 'lanches', 'bebidas', 'pagamento', 'taxa', 'total'];

  panelOpenState = false;
  constructor(private lancheService: LancheService, private bebidaService: BebidaService) {

  }




}
