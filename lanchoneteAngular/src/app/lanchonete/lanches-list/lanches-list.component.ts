import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Lanche } from '../model/lanche';

@Component({
  selector: 'app-lanches-list',
  templateUrl: './lanches-list.component.html',
  styleUrls: ['./lanches-list.component.scss']
})
export class LanchesListComponent {

  @Input() lanches: Lanche[] = [];

  readonly displayedColumns = ['nome', 'preco', 'descricao', 'action'];

  constructor(private router: Router, private route: ActivatedRoute) {

  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

}
