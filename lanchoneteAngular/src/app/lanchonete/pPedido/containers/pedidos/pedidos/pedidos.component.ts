import { EnderecoDialogComponent } from './../../../components/endereco-dialog/endereco-dialog.component';
import { ErrorDialogComponent } from './../../../../../shared/components/error-dialog/error-dialog.component';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(private lancheService: LancheService, private bebidaService: BebidaService, private pedidoService: PedidoService, private router: Router, private route: ActivatedRoute, public dialog: MatDialog) {
    this.refresh();
  }

  pedidos$: Observable<Pedido[]> | null = null;

  refresh() {
    this.pedidos$ = this.pedidoService.findAll().
      pipe(
        catchError(
          () => {
            this.onError('Erro ao carregar Pedidos.')
            return of([])
          }
        )
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  onHome() {
    this.router.navigate(['lanchonete']);
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }
  onEdit(pedido: Pedido) {
    console.log('edit');
  }
  onRemove(pedido: Pedido) {
    console.log('remove');
  }

  onEndereco() {
   this.dialog.open(EnderecoDialogComponent);
  }
}
