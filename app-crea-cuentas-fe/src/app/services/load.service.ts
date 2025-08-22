import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GenericService} from './generic.service';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoadService{

  url: string = `${environment.HOST}/clients`;

  constructor(
    private http: HttpClient
  ) { }

  upload(file: File){
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.url}/load`, formData, {
      headers: new HttpHeaders({}),
      responseType: 'text'
    })
  }

}
