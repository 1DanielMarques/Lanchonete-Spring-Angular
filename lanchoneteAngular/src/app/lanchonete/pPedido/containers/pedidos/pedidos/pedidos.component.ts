import { Component } from '@angular/core';
import { catchError, Observable, of } from 'rxjs';
import { Pedido } from 'src/app/lanchonete/model/pedido';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';

import { BebidaService } from './../../../../services/bebida/bebida.service';
import { LancheService } from './../../../../services/lanche/lanche.service';


@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.scss']
})
export class PedidosComponent {

  constructor(private lancheService: LancheService, private bebidaService: BebidaService, private pedidoService: PedidoService) {
    this.refresh();
  }

  pedidos$: Observable<Pedido[]> | null = null;
  data$: Pedido[] = [
    {
      id: '1',
      lanches: [{ id: '', nome: 'X-Frango', preco: '15.89', descricao: '' },{ id: '', nome: 'X-Bacon', preco: '15.89', descricao: '' }],
      qtdLanches: '1',
      bebidas: { id: '', nome: 'Suco', litragem: '2L', sabor: 'laranja', marca: 'Natu', preco: '12.99' },
      qtdBebidas: '2',
      taxa: '12.55',
      pagamento: 'DEBITO',
      total: '22.59'

    }
  ]

  readonly displayedColumnsLanche = ['id', 'lanches', 'bebidas', 'pagamento', 'taxa', 'total'];

  panelOpenState = false;

  refresh() {
    this.pedidos$ = this.pedidoService.findAll().
      pipe(
        catchError(
          () => {
            console.log('erro');
            return of([])
          }
        )
      );
  }
}
