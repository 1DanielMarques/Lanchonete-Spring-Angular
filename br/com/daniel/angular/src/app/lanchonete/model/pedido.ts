import { Bebida } from './bebida';
import { Endereco } from './endereco';
import { Lanche } from './lanche';

export interface Pedido {

  id: string,
  lanches: Lanche[],
  qtdLanches: string,
  bebidas: Bebida[],
  qtdBebidas: string,
  tipoPagamento: string,
  taxa: string,
  total: string;
  endereco: Partial<Endereco>;

}
