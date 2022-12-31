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

  findLanche(id: string) {
    return this.httpClient.get<boolean>(`${this.API}/${'lanche'}/${id}`);
  }

  deleteLanche(id: string) {
    return this.httpClient.delete(`${this.API}/${'lanche'}/${id}`);
  }

  findBebida(id: string) {
    return this.httpClient.get<boolean>(`${this.API}/${'bebida'}/${id}`);
  }
  deleteBebida(id: string) {
    return this.httpClient.delete(`${this.API}/${'bebida'}/${id}`);
  }

  save(pedido: Partial<Pedido>) {
    if (pedido.id) {
      return this.update(pedido);
    }
    return this.create(pedido);
  }
  private create(pedido: Partial<Pedido>) {
    return this.httpClient.post<Pedido>(this.API, pedido);
  }

  private update(pedido: Partial<Pedido>) {
    return this.httpClient.put<Pedido>(`${this.API}/${pedido.id}`, pedido);
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`);
  }

  calcTaxa(pagamento: string) {
    switch (pagamento) {
      case "DINHEIRO":
        return 0;
      case "DEBITO":
        return 1.25;
      case "CREDITO":
        return 2.75;
      default:
        return 10;

    }
  }

}
