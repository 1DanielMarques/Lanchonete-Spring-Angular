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

  save(endereco: Partial<Endereco>) {
    if (endereco.id) {
      return this.update(endereco);
    }
    return this.insert(endereco);
  }

  private insert(endereco: Partial<Endereco>) {
    return this.httpClient.post<Endereco>(this.API, endereco);
  }

  private update(endereco: Partial<Endereco>) {
    return this.httpClient.put<Endereco>(`${this.API}/${endereco.id}`, endereco);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`);
  }

  findById(id: string) {
    return this.httpClient.get<Endereco>(`${this.API}/${id}`);
  }

}
