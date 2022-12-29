import { Lanche } from './../../../../model/lanche';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';

@Component({
  selector: 'app-pedido-form',
  templateUrl: './pedido-form.component.html',
  styleUrls: ['./pedido-form.component.scss']
})
export class PedidoFormComponent {

  form = this.formBuilder.group({
    id: ['']
  });

  constructor(private formBuilder: NonNullableFormBuilder) {

  }
  panelOpenState = false;

  lanches: Lanche[] = [
    {id:'',nome:'X-Frango',preco:'',descricao:''},{id:'',nome:'X-Bacon',preco:'',descricao:''}
  ];

  onSubmit() {

  }
  onCancel() {

  }


}
