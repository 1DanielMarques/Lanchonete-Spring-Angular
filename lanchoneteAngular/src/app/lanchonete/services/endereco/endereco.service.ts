import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';

import { Endereco } from '../../model/endereco';

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {

  constructor(private httpClient: HttpClient) { }

  private readonly API = 'api/enderecos';

  findAll() {
    return this.httpClient.get<Endereco[]>(this.API).
      pipe(
        first(),
        //delay(5000),
        tap(b => console.log(b))
      )
  }

}
