import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppMaterialModule } from './../shared/app-material/app-material.module';
import { SharedModule } from './../shared/shared.module';
import { HomeComponent } from './home/home.component';
import { LanchoneteRoutingModule } from './lanchonete-routing.module';
import { BebidalistComponent } from './pBebida/components/bebidalist/bebidalist.component';
import { BebidaFormComponent } from './pBebida/containers/bebida-form/bebida-form.component';
import { BebidasComponent } from './pBebida/containers/bebidas/bebidas.component';
import { EnderecosListComponent } from './pEndereco/components/enderecos-list/enderecos-list.component';
import { EnderecoFormComponent } from './pEndereco/containers/endereco-form/endereco-form/endereco-form.component';
import { EnderecosComponent } from './pEndereco/containers/enderecos/enderecos.component';
import { LanchesListComponent } from './pLanche/components/lanches-list/lanches-list.component';
import { LancheFormComponent } from './pLanche/containers/lanche-form/lanche-form.component';
import { LanchesComponent } from './pLanche/containers/lanches/lanches.component';
import { PedidosComponent } from './pPedido/containers/pedidos/pedidos/pedidos.component';
import { PedidosListComponent } from './pPedido/components/pedidos-list/pedidos-list.component';
import { PedidoFormComponent } from './pPedido/containers/pedido-form/pedido-form/pedido-form.component';

@NgModule({
  declarations: [
    HomeComponent,
    LanchesComponent,
    LancheFormComponent,
    LanchesListComponent,
    BebidasComponent,
    BebidalistComponent,
    BebidaFormComponent,
    EnderecosComponent,
    EnderecosListComponent,
    EnderecoFormComponent,
    PedidosComponent,
    PedidosListComponent,
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
