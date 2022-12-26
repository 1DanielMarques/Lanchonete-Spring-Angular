import { Bebida } from './bebida';
import { Lanche } from './lanche';

export interface Pedido {

  id: string,
  lanches: Lanche[],
  qtdLanches: string,
  bebidas: Bebida[],
  qtdBebidas: string,
  tipoPagamento: string,
  taxa: string,
  total: string



}
