import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Lanche } from './../model/lanche';
import { LancheService } from './../services/lanche.service';

@Component({
  selector: 'app-lanches',
  templateUrl: './lanches.component.html',
  styleUrls: ['./lanches.component.scss'],
})
export class LanchesComponent implements OnInit {

  lanches: Observable<Lanche[]>;

  readonly displayedColumns = ['nome', 'preco', 'descricao'];

  constructor(private service: LancheService, private router : Router) {
    this.lanches = this.service.findAll();
  }

  ngOnInit(): void { }


  onHome() {
    this.router.navigate(['lanchonete']);
  }
}
