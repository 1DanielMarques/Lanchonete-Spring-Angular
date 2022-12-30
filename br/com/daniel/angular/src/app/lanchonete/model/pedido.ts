import { Lanche } from './lanche';
import { Endereco } from './endereco';
import { Bebida } from './bebida';

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
