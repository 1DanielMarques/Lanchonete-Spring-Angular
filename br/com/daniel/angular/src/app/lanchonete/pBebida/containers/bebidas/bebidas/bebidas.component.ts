import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { Bebida } from 'src/app/lanchonete/model/bebida';
import { BebidaService } from 'src/app/lanchonete/services/bebida/bebida.service';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog/confirm-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog/error-dialog.component';

@Component({
  selector: 'app-bebidas',
  templateUrl: './bebidas.component.html',
  styleUrls: ['./bebidas.component.scss']
})
export class BebidasComponent {

  bebidas$: Observable<Bebida[]> | null = null;

  constructor(private service: BebidaService, public dialog: MatDialog, private router: Router, private route: ActivatedRoute, private snackBar: MatSnackBar, private pedidoService: PedidoService) {
    this.refresh();
  }

  refresh() {
    this.bebidas$ = this.service.findAll().
      pipe(
        catchError(() => {
          this.onError('Erro ao carregar Bebidas.');
          return of([])
        })
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
  onEdit(bebida: Bebida) {
    this.router.navigate(['edit', bebida.id], { relativeTo: this.route });
  }

  onRemove(bebida: Bebida) {
    this.service.remove(bebida.id).subscribe(
      () => {
        this.refresh();
        this.snackBar.open('Bebida removida com sucesso!', '', { duration: 5000, verticalPosition: 'top', horizontalPosition: 'center' });
      },
      () => {
        if (this.pedidoService.findBebida(bebida.id)) {
          this.onErrorHasPedido('Esta Bebida tem um ou mais pedidos associados a ela.', 'Deseja excluir mesmo assim?', bebida);
        } else {
          this.onError('Erro ao tentar remover Bebida!');
        }

      }
    );
  }

  onErrorHasPedido(errorMsg: string, confirm: string, bebida: Bebida) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: [errorMsg, confirm, bebida.id, 'bebida']
    });
    dialogRef.afterClosed().subscribe((confirm: boolean) => {
      if (confirm) {
        this.refresh();
        this.snackBar.open('Bebida removida com sucesso!', '', { duration: 5000, verticalPosition: 'top', horizontalPosition: 'center' });
      }
    });
  }

}
