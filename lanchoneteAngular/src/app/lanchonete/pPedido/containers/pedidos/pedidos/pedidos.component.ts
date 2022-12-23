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
