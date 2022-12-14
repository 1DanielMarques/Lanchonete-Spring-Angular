import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { Endereco } from 'src/app/lanchonete/model/endereco';
import { Pedido } from 'src/app/lanchonete/model/pedido';
import { BebidaService } from 'src/app/lanchonete/services/bebida/bebida.service';
import { LancheService } from 'src/app/lanchonete/services/lanche/lanche.service';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog/confirm-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog/error-dialog.component';

import { EnderecoDialogComponent } from '../../../components/endereco-dialog/endereco-dialog/endereco-dialog.component';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.scss']
})
export class PedidosComponent {

  constructor(private lancheService: LancheService, private bebidaService: BebidaService, private pedidoService: PedidoService, private router: Router, private route: ActivatedRoute, public dialog: MatDialog, private snackBar: MatSnackBar) {
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
    this.router.navigate(['edit', pedido.id], { relativeTo: this.route });

  }

  onRemove(pedido: Pedido) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: ['Tem certeza que deseja excluir esse Pedido?', 'pedido']
    });

    dialogRef.afterClosed().subscribe(confirm => {
      if (confirm) {
        this.pedidoService.remove(pedido.id).subscribe(
          () => {
            this.refresh();
            this.snackBar.open('Pedido removido com sucesso!', '', {
              duration: 5000, horizontalPosition: 'center',
              verticalPosition: 'top',
            });
          },
          () => {
            this.onError('Erro ao tentar remover Pedido.');
          }
        );
      }
    });



  }

  onEndereco(endereco: Endereco) {
    this.dialog.open(EnderecoDialogComponent, {
      data:
        endereco

    });
  }
}


