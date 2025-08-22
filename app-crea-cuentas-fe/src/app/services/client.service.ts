import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {ClientsAccounts} from '../model/clientsAccounts';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  url: string = `${environment.HOST}/clients`;

  constructor(
    private http: HttpClient
  ) { }

  getClientsAccounts(){
    return this.http.get<ClientsAccounts[]>(`${this.url}`);
  }

}
