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

  onConfirm(confirm: boolean) {
    this.dialogRef.close(confirm);
  }

  verificaDados(data: string[]) {
    if(data[1] === 'lanche' || data[1] === 'bebida'){
      return false;
    }else{
      return true;
    }

  }

}
