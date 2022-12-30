import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { Lanche } from 'src/app/lanchonete/model/lanche';

import { Bebida } from './../../../../model/bebida';
import { BebidaService } from './../../../../services/bebida/bebida.service';
import { LancheService } from './../../../../services/lanche/lanche.service';

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

  lanches$:Observable<Lanche[]> | null = this.lancheService.findAll();

  bebidas$: Observable<Bebida[]> | null = this.bebidaService.findAll();

  qtd: Number = 0;

  constructor(private formBuilder: NonNullableFormBuilder, private location: Location, private lancheService: LancheService, private bebidaService: BebidaService) {

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
