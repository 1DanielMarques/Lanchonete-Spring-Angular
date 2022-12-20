import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Endereco } from 'src/app/lanchonete/model/endereco';

import { EnderecoService } from './../../services/endereco/endereco.service';

@Injectable({
  providedIn: 'root'
})
export class EnderecosResolver implements Resolve<Endereco> {
  constructor(private service: EnderecoService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Endereco> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    return of({ id: '', rua: '', numero: '', bairro: '' });
  }
}
