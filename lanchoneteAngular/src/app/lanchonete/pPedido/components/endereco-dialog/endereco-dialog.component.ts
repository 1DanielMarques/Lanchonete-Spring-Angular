import { Endereco } from 'src/app/lanchonete/model/endereco';
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-endereco-dialog',
  templateUrl: './endereco-dialog.component.html',
  styleUrls: ['./endereco-dialog.component.scss']
})
export class EnderecoDialogComponent {
constructor(@Inject(MAT_DIALOG_DATA) public endereco: Endereco){

}
}
