import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LanchoneteRoutingModule } from './lanchonete-routing.module';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    LanchoneteRoutingModule
  ]
})
export class LanchoneteModule { }
