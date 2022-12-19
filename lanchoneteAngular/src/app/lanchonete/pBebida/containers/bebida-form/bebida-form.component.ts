import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { BebidaService } from './../../../services/bebida/bebida.service';

@Component({
  selector: 'app-bebida-form',
  templateUrl: './bebida-form.component.html',
  styleUrls: ['./bebida-form.component.scss']
})
export class BebidaFormComponent {

  form = this.formBuilder.group({
    nome: [''],
    marca: [''],
    litragem: [''],
    sabor: [''],
    preco: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder, private service: BebidaService, private location: Location, private snackBar: MatSnackBar) {

  }

  onSubmit() {
    this.service.insert(this.form.value).subscribe(() => this.onSuccess(), () => this.onError());
    this.onCancel();
  }
  onCancel() {
    this.location.back();
  }

  onSuccess() {
    this.snackBar.open('Bebida salva com sucesso!', '', { duration: 5000 });
  }

  onError() {
    this.snackBar.open('Erro ao salvar Bebida.', '', { duration: 5000 });
  }

}
