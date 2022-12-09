import { Component, OnInit } from '@angular/core';

import { Lanche } from './../model/lanche';

@Component({
  selector: 'app-lanches',
  templateUrl: './lanches.component.html',
  styleUrls: ['./lanches.component.scss'],
})
export class LanchesComponent implements OnInit {
  readonly displayedColumns = ['nome', 'preco', 'descricao'];

  lanches: Lanche[] = [
    {
      id: '1',
      nome: 'X-Frango',
      preco: '15,55',
      descricao: 'Algumas coisa ae',
    },
  ];

  constructor() {}

  ngOnInit(): void {}
}
