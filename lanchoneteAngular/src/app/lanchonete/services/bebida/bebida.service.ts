import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap } from 'rxjs';

import { Bebida } from '../../model/bebida';

@Injectable({
  providedIn: 'root'
})
export class BebidaService {

  constructor(private httpClient: HttpClient) { }

  private readonly API = 'api/bebidas';


  findAll() {
    return this.httpClient.get<Bebida[]>(this.API).
      pipe(
        first(),
        //delay(5000),
        tap(b => console.log(b))
      );
  }

  insert(bebida: Partial<Bebida>) {
    return this.httpClient.post<Bebida>(this.API, bebida);
  }


  
}
