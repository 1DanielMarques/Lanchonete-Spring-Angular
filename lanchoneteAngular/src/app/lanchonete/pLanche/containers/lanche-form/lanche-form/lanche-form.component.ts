import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { Lanche } from 'src/app/lanchonete/model/lanche';
import { LancheService } from 'src/app/lanchonete/services/lanche/lanche.service';

@Component({
  selector: 'app-lanche-form',
  templateUrl: './lanche-form.component.html',
  styleUrls: ['./lanche-form.component.scss']
})
export class LancheFormComponent implements OnInit {

  form = this.formBuilder.group({
    id: [''],
    nome: [''],
    preco: [''],
    descricao: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: LancheService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute) {

  }
  ngOnInit(): void {
    const lanche: Lanche = this.route.snapshot.data['lanche'];
    this.form.setValue({
      id: lanche.id,
      nome: lanche.nome,
      preco: lanche.preco,
      descricao: lanche.descricao
    })
  }

  onSubmit() {
    this.service.save(this.form.value)
      .subscribe(
        () => this.onSuccess(),
        () => this.onError());
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

