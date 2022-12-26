import { Component } from '@angular/core';
import { FormControl, NonNullableFormBuilder } from '@angular/forms';

@Component({
  selector: 'app-pedido-form',
  templateUrl: './pedido-form.component.html',
  styleUrls: ['./pedido-form.component.scss']
})
export class PedidoFormComponent {

  form = this.formBuilder.group({
    id:['']
  });

  constructor(private formBuilder: NonNullableFormBuilder) {

  }



  onSubmit() {

  }
  onCancel() {

  }


}
