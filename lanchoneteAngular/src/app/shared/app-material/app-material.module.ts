import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';





@NgModule({
  exports:
    [
      MatButtonModule,
      MatCardModule,
      MatTableModule,
      MatToolbarModule,
      MatProgressSpinnerModule,
      MatDialogModule,
      MatIconModule,
      MatFormFieldModule,
      FormsModule,
      ReactiveFormsModule,
      MatInputModule,
      MatSnackBarModule,
      MatExpansionModule,
      MatDividerModule,
      MatListModule,

    ],
})
export class AppMaterialModule { }
