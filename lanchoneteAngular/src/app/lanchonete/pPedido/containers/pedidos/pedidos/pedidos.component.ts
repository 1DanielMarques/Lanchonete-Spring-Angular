import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
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

  constructor(private lancheService: LancheService, private bebidaService: BebidaService, private pedidoService: PedidoService, private router: Router, private route: ActivatedRoute) {
    this.refresh();
  }

  pedidos$: Observable<Pedido[]> | null = null;

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

  onHome() {
    this.router.navigate(['lanchonete']);
  }

  onAdd() {
    console.log('ADD');
  }
  onEdit(pedido: Pedido) {
    console.log('edit');
  }
  onRemove(pedido: Pedido) {
    console.log('remove');
  }
}
