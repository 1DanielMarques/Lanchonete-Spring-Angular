import { MatSnackBar } from '@angular/material/snack-bar';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, catchError, of } from 'rxjs';
import { Endereco } from 'src/app/lanchonete/model/endereco';
import { Pedido } from 'src/app/lanchonete/model/pedido';
import { BebidaService } from 'src/app/lanchonete/services/bebida/bebida.service';
import { LancheService } from 'src/app/lanchonete/services/lanche/lanche.service';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';
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

  onEndereco(endereco: Endereco) {
    this.dialog.open(EnderecoDialogComponent, {
      data:
        endereco

    });
  }
}

