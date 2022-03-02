import { Component, OnInit } from '@angular/core';
import { ServiciosService } from "../../services/servicios.service";
import { Tareas } from "../../tareas";
@Component({
  selector: 'app-listar-tareas',
  templateUrl: './listar-tareas.component.html',
  styleUrls: ['./listar-tareas.component.css']
})
export class ListarTareasComponent implements OnInit {
  tareas: Tareas[]=[];
  selectedTarea?: Tareas;
  constructor(private apiservice: ServiciosService) {
    //console.log("VerfraseComponent::constructor()");
  }

 

  onSelect(tarea: Tareas): void {
    this.selectedTarea = tarea;

  }

  ngOnInit() {
  //  this.apiservice.getListaTareas().subscribe(tareas => (this.tareas = tareas));
    console.log("ngOnInit. FIN");
    this.apiservice.getListaTareas().subscribe(tareas => this.tareas=tareas)
    console.log( this.tareas[0].nombre);
  }

}
