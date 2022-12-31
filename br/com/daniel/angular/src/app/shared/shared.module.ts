import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './app-material/app-material/app-material.module';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog/confirm-dialog.component';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog/error-dialog.component';



@NgModule({
  declarations: [
    ErrorDialogComponent,
    ConfirmDialogComponent
  ],
  imports: [
    CommonModule, AppMaterialModule
  ],
  exports: [ErrorDialogComponent]
})
export class SharedModule { }
