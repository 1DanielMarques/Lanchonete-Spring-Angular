import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';
import { Pedido } from '../../model/pedido';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  private readonly API = "api/pedidos";

  constructor(private httpClient: HttpClient) {
  }

  findAll() {
    return this.httpClient.get<Pedido[]>(this.API).pipe(
      first(),
      tap(l => console.log(l))
    );
  }

  findById(id: string) {
    return this.httpClient.get<Pedido>(`${this.API}/${id}`);
  }

  save(pedido: Partial<Pedido>) {
    return this.create(pedido);
  }
  private create(pedido: Partial<Pedido>) {
    return this.httpClient.post<Pedido>(this.API, pedido);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`);
  }

}
