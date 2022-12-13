import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LancheFormComponent } from './pLanche/containers/lanche-form/lanche-form.component';
import { LanchesComponent } from './pLanche/containers/lanches/lanches.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'lanches', component: LanchesComponent },
  { path: 'lanches/new', component: LancheFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LanchoneteRoutingModule { }
