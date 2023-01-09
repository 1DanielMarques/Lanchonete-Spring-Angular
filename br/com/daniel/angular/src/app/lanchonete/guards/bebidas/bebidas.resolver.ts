import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Bebida } from '../../model/bebida';
import { BebidaService } from '../../services/bebida/bebida.service';

@Injectable({
  providedIn: 'root'
})
export class BebidasResolver implements Resolve<Bebida> {

  constructor(private service: BebidaService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Bebida> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    return of({ id: '', nome: '', marca: '', litragem: '', sabor: '', preco: '', qtd: 0 });

  }
}
