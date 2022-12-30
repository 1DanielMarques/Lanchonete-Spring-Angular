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

}
