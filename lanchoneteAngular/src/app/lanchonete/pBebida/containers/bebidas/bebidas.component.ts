import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, catchError, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Bebida } from '../../../model/bebida';
import { BebidaService } from '../../../services/bebida/bebida.service';

@Component({
  selector: 'app-bebidas',
  templateUrl: './bebidas.component.html',
  styleUrls: ['./bebidas.component.scss']
})
export class BebidasComponent {

  bebidas$: Observable<Bebida[]> | null = null;

  constructor(private service: BebidaService, public dialog: MatDialog, private router: Router, private route: ActivatedRoute) {
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



}
