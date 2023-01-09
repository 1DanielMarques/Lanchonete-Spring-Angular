import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Pedido } from 'src/app/lanchonete/model/pedido';
import { PedidoService } from 'src/app/lanchonete/services/pedido/pedido.service';

@Injectable({
  providedIn: 'root'
})
export class PedidosResolver implements Resolve<Pedido> {
  constructor(private service: PedidoService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Pedido> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    return of(
      {
        id: '',
        lanches: [{ id: '', nome: '', descricao: '', preco: '', qtd: 0 }],
        qtdLanches: '',
        bebidas: [{ id: '', nome: '', marca: '', litragem: '', sabor: '', preco: '', qtd: 0 }],
        qtdBebidas: '',
        tipoPagamento: '',
        taxa: '0',
        total: '0',
        endereco: { id: '', rua: '', bairro: '', numero: '' }

      });
  }
}
