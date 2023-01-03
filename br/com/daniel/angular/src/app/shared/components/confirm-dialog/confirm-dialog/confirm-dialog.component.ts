import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';

@Component({
  selector: 'app-confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
  styleUrls: ['./confirm-dialog.component.scss']
})
export class ConfirmDialogComponent {
  constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: string[], private pedidoService: PedidoService) { }

  onSim(id: string, tipo: string, confirm: boolean) {
    if (id && tipo == 'lanche') {
      this.pedidoService.deleteLanche(id).subscribe();
    } else if (id && tipo == 'bebida') {
      this.pedidoService.deleteBebida(id).subscribe();
    }
    this.dialogRef.close(confirm);
  }

}
