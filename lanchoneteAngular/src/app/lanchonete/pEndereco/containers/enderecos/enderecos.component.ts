import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { Endereco } from 'src/app/lanchonete/model/endereco';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { EnderecoService } from './../../../services/endereco/endereco.service';

@Component({
  selector: 'app-enderecos',
  templateUrl: './enderecos.component.html',
  styleUrls: ['./enderecos.component.scss']
})
export class EnderecosComponent {

  enderecos$: Observable<Endereco[]> | null = null;

  constructor(private router: Router, private service: EnderecoService, public dialog: MatDialog, private route: ActivatedRoute) {
    this.refresh();

  }

  refresh() {
    this.enderecos$ = this.service.findAll().
      pipe(
        catchError(
          () => {
            this.onError('Erro ao carregar Endereços.');
            return of([])
          }
        )
      );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onHome() {
    this.router.navigate(['lanchonete']);
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route })
  }
  onEdit(endereco: Endereco) {
    this.router.navigate(['edit', endereco.id], { relativeTo: this.route })
  }

}
