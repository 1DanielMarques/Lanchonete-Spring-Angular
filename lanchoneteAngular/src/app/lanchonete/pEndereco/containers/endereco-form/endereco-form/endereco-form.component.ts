import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

import { EnderecoService } from './../../../../services/endereco/endereco.service';

@Component({
  selector: 'app-endereco-form',
  templateUrl: './endereco-form.component.html',
  styleUrls: ['./endereco-form.component.scss']
})
export class EnderecoFormComponent implements OnInit {

  form = this.formBuilder.group({
    id: [''],
    rua: [''],
    numero: [''],
    bairro: ['']
  });


  constructor(private formBuilder: NonNullableFormBuilder, private service: EnderecoService, private snackBar: MatSnackBar, private location: Location, private route: ActivatedRoute) {

  }
  ngOnInit(): void {
    const endereco = this.route.snapshot.data['endereco'];
    this.form.setValue({
      id: endereco.id,
      rua: endereco.rua,
      numero: endereco.numero,
      bairro: endereco.bairro
    });
  }

  onSubmit() {
    this.service.save(this.form.value).subscribe();
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
