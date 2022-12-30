import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { Lanche } from '../../model/lanche';
import { LancheService } from '../../services/lanche/lanche.service';

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
    return of({ id: '', nome: '', preco: '', descricao: '', qtd: 0 });
  }
}
