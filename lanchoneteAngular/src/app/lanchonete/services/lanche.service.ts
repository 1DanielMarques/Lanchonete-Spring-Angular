import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, tap } from 'rxjs';

import { Lanche } from './../model/lanche';

@Injectable({
  providedIn: 'root',
})
export class LancheService {

  constructor(private httpClient: HttpClient) { }

  private readonly API = 'api/lanches';

  findAll() {
    return this.httpClient.get<Lanche[]>(this.API).
      pipe(
        first(),
        //delay(5000),
        tap(l => console.log(l))
      );
  }
}
