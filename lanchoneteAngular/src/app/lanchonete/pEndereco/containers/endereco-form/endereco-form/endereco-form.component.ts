import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { EnderecoService } from './../../../../services/endereco/endereco.service';

@Component({
  selector: 'app-endereco-form',
  templateUrl: './endereco-form.component.html',
  styleUrls: ['./endereco-form.component.scss']
})
export class EnderecoFormComponent {

  form = this.formBuilder.group({
    rua: [''],
    numero: [''],
    bairro: ['']
  });


  constructor(private formBuilder: NonNullableFormBuilder, private service: EnderecoService, private snackBar: MatSnackBar, private location: Location) {

  }

  onSubmit() {
    this.service.insert(this.form.value).subscribe();
    this.onCancel();
  }
  onCancel() {
    this.location.back();
  }

  onSuccess() {
    this.snackBar.open('Endereço salvo com sucesso!', '', { duration: 5000 });
  }
  onError() {
    this.snackBar.open('Erro ao salvar Endereço.', '', { duration: 5000 });
  }

}
