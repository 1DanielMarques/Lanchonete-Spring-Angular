import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteServiceService {

  private readonly API = 'api/lanchonete';

  constructor(private httpClient: HttpClient) {}

  /*list() {
    return this.httpClient.get<Course[]>(this.API).pipe(
      first(),
      // delay(3000),
      tap((courses) => console.log(courses))
    );*/
}
