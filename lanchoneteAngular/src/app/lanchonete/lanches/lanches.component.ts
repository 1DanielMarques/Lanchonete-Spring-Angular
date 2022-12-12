import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Lanche } from './../model/lanche';
import { LancheService } from './../services/lanche.service';

@Component({
  selector: 'app-lanches',
  templateUrl: './lanches.component.html',
  styleUrls: ['./lanches.component.scss'],
})
export class LanchesComponent implements OnInit {

  lanches$: Observable<Lanche[]>;

  readonly displayedColumns = ['nome', 'preco', 'descricao', 'action'];

  constructor(private service: LancheService, private router: Router, public dialog: MatDialog, private route: ActivatedRoute) {
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
}
