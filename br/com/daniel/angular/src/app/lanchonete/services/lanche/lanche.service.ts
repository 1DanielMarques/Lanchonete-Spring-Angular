import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';

import { Lanche } from '../../model/lanche';

@Injectable({
  providedIn: 'root'
})
export class LancheService {

  constructor(private httpClient: HttpClient) { }

  private readonly API = 'api/lanches';

  findAll() {
    return this.httpClient.get<Lanche[]>(this.API).
      pipe(
        first(),
        // delay(5000),
        tap(l => console.log(l))
      );
  }

  findLanchesPedido(id:string){
    return this.httpClient.get<Lanche[]>(`${this.API}/${'pedido'}/${id}`);
  }

  save(lanche: Partial<Lanche>) {
    if (lanche.id) {
      return this.update(lanche);
    }
    return this.create(lanche);
  }

  private create(lanche: Partial<Lanche>) {
    return this.httpClient.post<Lanche>(this.API, lanche);
  }

  private update(lanche: Partial<Lanche>) {
    return this.httpClient.put<Lanche>(`${this.API}/${lanche.id}`, lanche);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`);
  }

  findById(id: string) {
    return this.httpClient.get<Lanche>(`${this.API}/${id}`);
  }

}

