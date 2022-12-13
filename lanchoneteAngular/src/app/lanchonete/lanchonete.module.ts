import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './../shared/app-material/app-material.module';
import { SharedModule } from './../shared/shared.module';
import { HomeComponent } from './home/home.component';
import { LanchoneteRoutingModule } from './lanchonete-routing.module';
import { LanchesListComponent } from './pLanche/components/lanches-list/lanches-list.component';
import { LancheFormComponent } from './pLanche/containers/lanche-form/lanche-form.component';
import { LanchesComponent } from './pLanche/containers/lanches/lanches.component';

@NgModule({
  declarations: [
    HomeComponent,
    LanchesComponent,
    LancheFormComponent,
    LanchesListComponent
  ],
  imports: [
    CommonModule,
    LanchoneteRoutingModule,
    AppMaterialModule,
    SharedModule
  ]
})
export class LanchoneteModule { }
