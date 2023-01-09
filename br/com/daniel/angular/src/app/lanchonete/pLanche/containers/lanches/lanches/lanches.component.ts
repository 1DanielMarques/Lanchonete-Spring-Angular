import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, firstValueFrom, Observable, of } from 'rxjs';
import { Lanche } from 'src/app/lanchonete/model/lanche';
import { LancheService } from 'src/app/lanchonete/services/lanche/lanche.service';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog/error-dialog.component';

import {
  ConfirmDialogComponent,
} from './../../../../../shared/components/confirm-dialog/confirm-dialog/confirm-dialog.component';
import { PedidoService } from './../../../../services/pedido/pedido.service';

@Component({
  selector: 'app-lanches',
  templateUrl: './lanches.component.html',
  styleUrls: ['./lanches.component.scss']
})
export class LanchesComponent implements OnInit {

  lanches$: Observable<Lanche[]> | null = null;

  constructor(
    private service: LancheService,
    private router: Router,
    public dialog: MatDialog,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private pedidoService: PedidoService) {
    this.refresh();
  }

  refresh() {
    this.lanches$ = this.service.findAll().
      pipe(
        catchError(error => {
          this.onError('Erro ao carregar Lanches.');
          return of([])
        })
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  ngOnInit(): void { }


  onHome() {
    this.router.navigate(['lanchonete']);
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(lanche: Lanche) {
    this.router.navigate(['edit', lanche.id], { relativeTo: this.route });
  }

  async onRemove(lanche: Lanche) {
    if (await firstValueFrom(this.pedidoService.findLanche(lanche.id))) {
      console.log(firstValueFrom(this.pedidoService.findLanche(lanche.id)));
      this.onErrorHasPedido('Este Lanche tem um ou mais pedidos associados a ele.', 'Deseja excluir mesmo assim?', lanche);
    } else {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        data: ['Tem certeza que deseja excluir esse Lanche?', 'lanche']
      });
      dialogRef.afterClosed().subscribe((confirm: boolean) => {
        if (confirm) {
          this.service.remove(lanche.id).subscribe(() => {
            this.refresh();
            return this.snackBar.open('Lanche removido com sucesso!', '', { duration: 5000, verticalPosition: 'top', horizontalPosition: 'center' });
          },
            () => this.onError('Erro ao tentar remover Lanche')
          );
        }
      });
    }
  }


  onErrorHasPedido(errorMsg: string, confirm: string, lanche: Lanche) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: [errorMsg, confirm]
    });
    dialogRef.afterClosed().subscribe((confirm: boolean) => {
      if (confirm) {
        this.pedidoService.deleteLanche(lanche.id).subscribe(() => {
          this.refresh();
          this.snackBar.open('Lanche removido com sucesso!', '', { duration: 5000, verticalPosition: 'top', horizontalPosition: 'center' });
        },
          () => this.onError('Erro ao tentar remover Lanche')
        );
      }
    });
  }
}
