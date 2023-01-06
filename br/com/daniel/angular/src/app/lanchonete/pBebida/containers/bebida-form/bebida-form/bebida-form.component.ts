import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { BebidaService } from 'src/app/lanchonete/services/bebida/bebida.service';

@Component({
  selector: 'app-bebida-form',
  templateUrl: './bebida-form.component.html',
  styleUrls: ['./bebida-form.component.scss']
})
export class BebidaFormComponent implements OnInit {

  form = this.formBuilder.group({
    id: [''],
    nome: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
    marca: ['', [Validators.required, Validators.maxLength(50)]],
    litragem: ['', [Validators.required, Validators.maxLength(5)]],
    sabor: ['', [Validators.maxLength(50)]],
    preco: ['', [Validators.required, Validators.max(99.99)]]
  });

  constructor(private formBuilder: NonNullableFormBuilder, private service: BebidaService, private location: Location, private snackBar: MatSnackBar, private route: ActivatedRoute) {

  }
  ngOnInit(): void {
    const bebida = this.route.snapshot.data['bebida'];
    this.form.setValue({
      id: bebida.id,
      nome: bebida.nome,
      marca: bebida.marca,
      litragem: bebida.litragem,
      sabor: bebida.sabor,
      preco: bebida.preco
    })
  }

  onSubmit() {
    if (this.form.valid) {
      this.service.save(this.form.value).subscribe(
        () => this.onSuccess(),
        () => this.onError());
    } else {
      this.snackBar.open('Formulário inválido', '', { duration: 5000 });
    }

  }
  onCancel() {
    this.location.back();
  }

  onSuccess() {
    this.onCancel();
    this.snackBar.open('Bebida salva com sucesso!', '', { duration: 5000 });
  }

  onError() {
    this.onCancel();
    this.snackBar.open('Erro ao salvar Bebida.', '', { duration: 5000 });
  }

  getErrorMessage(formField: string) {
    const field = this.form.get(formField);
    if (field?.hasError('required')) {
      return 'Campo obrigatório.';
    }

    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 3;
      return `Mínimo de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 50;
      return `Máximo de ${requiredLength} caracteres.`;
    }

    if (field?.hasError('max')) {
      const requiredLength: number = field.errors ? field.errors['max']['requiredLength'] : 5;
      return `Valor máximo excedido`;
    }
    return 'Campo inválido.';
  }



}

