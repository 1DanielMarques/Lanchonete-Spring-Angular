import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void { }

  onLanches() {
    this.router.navigate(['lanches'], { relativeTo: this.route });
  }

  onBebidas() {
    this.router.navigate(['bebidas'], { relativeTo: this.route });
  }

  onEnderecos() {
    this.router.navigate(['enderecos'], { relativeTo: this.route });
  }

}
