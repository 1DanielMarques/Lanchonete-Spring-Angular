import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Endereco } from 'src/app/lanchonete/model/endereco';

@Component({
  selector: 'app-enderecos-list',
  templateUrl: './enderecos-list.component.html',
  styleUrls: ['./enderecos-list.component.scss']
})
export class EnderecosListComponent {

  @Input() enderecos: Endereco[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() remove = new EventEmitter(false);

  readonly displayedColumns = ['rua', 'numero', 'bairro', 'action'];

  constructor() {

  }

  onAdd() {
    this.add.emit(true);
  }
  onEdit(endereco: Endereco) {
    this.edit.emit(endereco);
  }

  onRemove(endereco: Endereco) {
    this.remove.emit(endereco);
  }

}
