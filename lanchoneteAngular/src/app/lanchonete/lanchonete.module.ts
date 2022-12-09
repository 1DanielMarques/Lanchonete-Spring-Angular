import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './../shared/app-material/app-material.module';
import { HomeComponent } from './home/home.component';
import { LanchoneteRoutingModule } from './lanchonete-routing.module';
import { LanchesComponent } from './lanches/lanches.component';


@NgModule({
  declarations: [
    HomeComponent,
    LanchesComponent
  ],
  imports: [
    CommonModule,
    LanchoneteRoutingModule,
    AppMaterialModule
  ]
})
export class LanchoneteModule { }
