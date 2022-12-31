import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss']
})
export class ConfirmDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: string[], private pedidoService: PedidoService) { }

  onSim(id: string) {
   this.pedidoService.deleteLanche(id).subscribe();
  }
  

}
