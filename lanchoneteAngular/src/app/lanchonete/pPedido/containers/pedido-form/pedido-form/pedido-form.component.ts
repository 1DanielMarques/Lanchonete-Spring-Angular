import { Bebida } from './../../../../model/bebida';
import { Lanche } from 'src/app/lanchonete/model/lanche';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-pedido-form',
  templateUrl: './pedido-form.component.html',
  styleUrls: ['./pedido-form.component.scss']
})
export class PedidoFormComponent {

  form = this.formBuilder.group({
    id: ['']
  });

  panelOpenState = false;

  lanches: Lanche[] = [
    { id: '', nome: 'X-Frango', preco: '', descricao: '', qtd: 0 }, { id: '', nome: 'X-Bacon', preco: '', descricao: '', qtd: 0 }
  ];

  bebidas: Bebida[] = [
    { id: '', nome: 'Refrigerante', marca: '', litragem: '', sabor: '', preco: '',qtd:0 }, { id: '', nome: 'Suco', marca: '', litragem: '', sabor: '', preco: '',qtd:0 }
  ]

  qtd: Number = 0;

  constructor(private formBuilder: NonNullableFormBuilder, private location: Location) {

  }


  onSubmit() {
    this.onCancel();
  }
  onCancel() {
    this.location.back();
  }

  onAdd(item: any) {
    item.qtd++;
  }
  onRemove(item: any) {
    if (item.qtd > 0) {
      item.qtd--;
    } else {
      item.qtd = 0;
    }

  }


}
