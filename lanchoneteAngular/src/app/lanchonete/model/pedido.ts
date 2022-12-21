import { Observable } from 'rxjs';

import { Bebida } from './bebida';
import { Lanche } from './lanche';

export interface Pedido {

  id: string,
  //cliente: Cliente,
  lanches: Lanche[],
  qtdLanches: string,
  bebidas: Observable<Bebida[]>,
  qtdBebidas: string,
  pagamento: string,
  taxa: string,
  total: string



}
