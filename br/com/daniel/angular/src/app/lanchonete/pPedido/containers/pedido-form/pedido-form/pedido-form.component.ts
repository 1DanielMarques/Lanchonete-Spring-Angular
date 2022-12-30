import { ErrorDialogComponent } from './../../../../../shared/components/error-dialog/error-dialog/error-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';
import { Pedido } from 'src/app/lanchonete/model/pedido';
import { Endereco } from './../../../../model/endereco';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { Observable, catchError, of } from 'rxjs';
import { Lanche } from 'src/app/lanchonete/model/lanche';

import { Bebida } from './../../../../model/bebida';
import { BebidaService } from './../../../../services/bebida/bebida.service';
import { LancheService } from './../../../../services/lanche/lanche.service';

@Component({
  selector: 'app-pedido-form',
  templateUrl: './pedido-form.component.html',
  styleUrls: ['./pedido-form.component.scss']
})
export class PedidoFormComponent implements OnInit {

  form = this.formBuilder.group({
    tipoPagamento: [''],
    rua: [''],
    bairro: [''],
    numero: ['']

  });

  lanches$: Observable<Lanche[]> | null = null;

  bebidas$: Observable<Bebida[]> | null = null;

  endereco: Partial<Endereco> = { id: '', rua: '', bairro: '', numero: '' };
  pedido: Partial<Pedido> = {
    lanches: [],
    bebidas: [],
    tipoPagamento: '',
    endereco: this.endereco
  };

  constructor(private formBuilder: NonNullableFormBuilder, private location: Location, private lancheService: LancheService, private bebidaService: BebidaService, private pedidoService: PedidoService, private snackBar: MatSnackBar, private dialog: MatDialog) {
    this.onRefresh();
  }
  ngOnInit(): void {

  }

  onRefresh() {

    this.lanches$ = this.lancheService.findAll().
      pipe(
        catchError(() => {
          this.onError('Erro ao carregar Lanches.');
          return of([])
        })
      );

    this.bebidas$ = this.bebidaService.findAll().
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
    })
  }

  setData() {
    this.endereco.rua = this.form.value.rua;
    this.endereco.bairro = this.form.value.bairro;
    this.endereco.numero = this.form.value.numero;
    this.pedido.tipoPagamento = this.form.value.tipoPagamento;

  }

  onSubmit() {
    this.setData();
    this.pedidoService.save(this.pedido).subscribe(
      () => this.onSuccessSubmit(),
      () => this.onErrorSubmit());

  }

  onSuccessSubmit() {
    this.onCancel();
    this.snackBar.open('Pedido salvo com sucesso!', '', { duration: 5000 });
  }

  onErrorSubmit() {
    this.onCancel();
    this.snackBar.open('Erro ao salvar Pedido', '', { duration: 5000 });
  }

  onAddLanche(lanche: Lanche) {
    lanche.qtd++;
    this.pedido.lanches?.push(lanche);

  }

  onRemoveLanche(lanche: Lanche) {
    if (lanche.qtd > 0) {
      lanche.qtd--;
      this.pedido.lanches?.splice(this.pedido.lanches?.indexOf(lanche, 0), 1);
    } else {
      lanche.qtd = 0;
    }
  }

  onAddBebida(bebida: Bebida) {
    bebida.qtd++;
    this.pedido.bebidas?.push(bebida);
  }

  onRemoveBebida(bebida: Bebida) {
    if (bebida.qtd > 0) {
      bebida.qtd--;
      this.pedido.bebidas?.splice(this.pedido.bebidas?.indexOf(bebida, 0), 1);
    } else {
      bebida.qtd = 0;
    }
  }

  onCancel() {
    this.location.back();
  }

}
