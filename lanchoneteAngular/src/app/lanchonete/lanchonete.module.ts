import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './../shared/app-material/app-material.module';
import { SharedModule } from './../shared/shared.module';
import { HomeComponent } from './home/home.component';
import { LanchesComponent } from './lanches/lanches.component';
import { LanchoneteRoutingModule } from './lanchonete-routing.module';
import { LancheFormComponent } from './forms/lanche-form/lanche-form.component';


@NgModule({
  declarations: [
    HomeComponent,
    LanchesComponent,
    LancheFormComponent
  ],
  imports: [
    CommonModule,
    LanchoneteRoutingModule,
    AppMaterialModule,
    SharedModule
  ]
})
export class LanchoneteModule { }
