import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { LancheService } from 'src/app/lanchonete/services/lanche/lanche.service';

import { Lanche } from '../../model/lanche';



@Injectable({
  providedIn: 'root'
})
export class LanchesResolver implements Resolve<Lanche> {

  constructor(private service: LancheService) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Lanche> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    return of({ id: '', nome: '', preco: '', descricao: '' });
  }
}
