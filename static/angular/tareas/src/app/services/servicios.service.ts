import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Tareas } from '../tareas';
@Injectable({
  providedIn: 'root'
})
export class ServiciosService {


  private tareasURL = "http://localhost:8080/consulta/allProductos"; // URL to web api

  constructor(private http: HttpClient) {}

  public getListaTareas(): Observable<Tareas[]> {
    return this.http.get<Tareas[]>(this.tareasURL);

  }

  
}
