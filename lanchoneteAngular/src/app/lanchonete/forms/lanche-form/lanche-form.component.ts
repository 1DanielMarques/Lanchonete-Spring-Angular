import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-lanche-form',
  templateUrl: './lanche-form.component.html',
  styleUrls: ['./lanche-form.component.scss']
})
export class LancheFormComponent {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      nome: [null],
      preco: [null],
      descricao: [null]
    });
  }

  onSubmit() {

  }
  onCancel() {

  }

}
