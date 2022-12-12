import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LancheFormComponent } from './forms/lanche-form/lanche-form.component';
import { HomeComponent } from './home/home.component';
import { LanchesComponent } from './lanches/lanches.component';

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
