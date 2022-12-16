import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LanchesResolver } from './guards/lanches.resolver';
import { HomeComponent } from './home/home.component';
import { BebidaFormComponent } from './pBebida/containers/bebida-form/bebida-form.component';
import { BebidasComponent } from './pBebida/containers/bebidas/bebidas.component';
import { EnderecoFormComponent } from './pEndereco/containers/endereco-form/endereco-form/endereco-form.component';
import { EnderecosComponent } from './pEndereco/containers/enderecos/enderecos.component';
import { LancheFormComponent } from './pLanche/containers/lanche-form/lanche-form.component';
import { LanchesComponent } from './pLanche/containers/lanches/lanches.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'lanches', component: LanchesComponent },
  { path: 'lanches/new', component: LancheFormComponent, resolve: { lanche: LanchesResolver } },
  { path: 'lanches/edit/:id', component: LancheFormComponent, resolve: { lanche: LanchesResolver } },
  { path: 'bebidas', component: BebidasComponent },
  { path: 'bebidas/new', component: BebidaFormComponent },
  { path: 'enderecos', component: EnderecosComponent },
  { path: 'enderecos/new', component: EnderecoFormComponent },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LanchoneteRoutingModule { }
