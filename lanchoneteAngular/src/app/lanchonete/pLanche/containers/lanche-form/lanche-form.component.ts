import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LancheService } from 'src/app/lanchonete/services/lanche/lanche.service';

@Component({
  selector: 'app-lanche-form',
  templateUrl: './lanche-form.component.html',
  styleUrls: ['./lanche-form.component.scss']
})
export class LancheFormComponent {

  form = this.formBuilder.group({
    nome: [''],
    preco: [''],
    descricao: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder, private service: LancheService, private snackBar: MatSnackBar, private location: Location) {

  }

  onSubmit() {
    this.service.save(this.form.value)
      .subscribe(
        result => this.onSuccess(),
        error => this.onError());
  }

  private onError() {
    return this.snackBar.open('Erro ao salvar Lanche', '', { duration: 5000 });
  }

  private onSuccess() {
    this.onCancel();
    return this.snackBar.open('Lanche salvo com sucesso!', '', { duration: 5000 });
  }
  onCancel() {
    this.location.back();
  }

}
