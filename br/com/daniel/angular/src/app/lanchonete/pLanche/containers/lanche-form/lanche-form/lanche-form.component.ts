import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
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
    nome: ['', [Validators.required, Validators.maxLength(50)]],
    preco: ['', [Validators.required, Validators.max(99.99)]],
    descricao: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(150)]]
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
    if (this.form.valid) {
      this.service.save(this.form.value)
        .subscribe(
          () => this.onSuccess(),
          () => this.onError());
    } else {
      this.snackBar.open('Formulário inválido', '', { duration: 5000 });
    }

  }

  private onError() {
    this.onCancel();
    return this.snackBar.open('Erro ao salvar Lanche', '', { duration: 5000 });
  }

  private onSuccess() {
    this.onCancel();
    return this.snackBar.open('Lanche salvo com sucesso!', '', { duration: 5000 });
  }
  
  onCancel() {
    this.location.back();
  }

  getErrorMessage(formField: string) {
    const field = this.form.get(formField);
    if (field?.hasError('required')) {
      return 'Campo obrigatório.';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Mínimo de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 150;
      return `Máximo de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('max')) {
      const requiredLength: number = field.errors ? field.errors['max']['requiredLength'] : 5;
      return `Valor máximo excedido`;
    }

    return 'Campo inválido.';
  }
}

