import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { HomeComponent } from './home/home.component';
import { LanchoneteRoutingModule } from './lanchonete-routing.module';
import { BebidasListComponent } from './pBebida/components/bebidas-list/bebidas-list.component';
import { BebidaFormComponent } from './pBebida/containers/bebida-form/bebida-form/bebida-form.component';
import { BebidasComponent } from './pBebida/containers/bebidas/bebidas/bebidas.component';
import { LanchesListComponent } from './pLanche/components/lanches-list/lanches-list.component';
import { LancheFormComponent } from './pLanche/containers/lanche-form/lanche-form/lanche-form.component';
import { LanchesComponent } from './pLanche/containers/lanches/lanches/lanches.component';
import { EnderecoDialogComponent } from './pPedido/components/endereco-dialog/endereco-dialog/endereco-dialog.component';
import { PedidosListComponent } from './pPedido/components/pedidos-list/pedidos-list/pedidos-list.component';
import { PedidoFormComponent } from './pPedido/containers/pedido-form/pedido-form/pedido-form.component';
import { PedidosComponent } from './pPedido/containers/pedidos/pedidos/pedidos.component';


@NgModule({
  declarations: [
    HomeComponent,
    LanchesListComponent,
    LanchesComponent,
    LancheFormComponent,
    BebidasListComponent,
    BebidasComponent,
    BebidaFormComponent,
    PedidosListComponent,
    EnderecoDialogComponent,
    PedidosComponent,
    PedidoFormComponent
  ],
  imports: [
    CommonModule,
    LanchoneteRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class LanchoneteModule { }
