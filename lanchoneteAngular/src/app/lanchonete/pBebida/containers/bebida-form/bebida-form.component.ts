import { NonNullableFormBuilder } from '@angular/forms';
import { Component } from '@angular/core';

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

  constructor(private formBuilder: NonNullableFormBuilder) {

  }

}
