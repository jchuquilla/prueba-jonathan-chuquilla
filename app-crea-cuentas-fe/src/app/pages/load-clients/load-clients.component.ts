import {Component, OnInit} from '@angular/core';
import {LoadService} from '../../services/load.service';
import {NgIf} from '@angular/common';
import {MaterialModule} from '../../material/material.module';
import {ClientsAccounts} from '../../model/clientsAccounts';
import {MatTableDataSource} from '@angular/material/table';
import {ClientService} from '../../services/client.service';

@Component({
  selector: 'app-load-clients',
  imports: [
    NgIf, MaterialModule
  ],
  templateUrl: './load-clients.component.html',
  styleUrl: './load-clients.component.css'
})
export class LoadClientsComponent implements OnInit {

  selectedFile: File | null = null;
  fileName: string | null = null;
  dataSource: MatTableDataSource<ClientsAccounts>;

  columnDefinitions = [
    {def: 'firstName', label: 'firstName', hide: false},
    {def: 'lastName', label: 'lastName', hide: false},
    {def: 'identificationNumber', label: 'identificationNumber', hide: false},
    {def: 'age', label: 'age', hide: false},
    {def: 'email', label: 'email', hide: false},
    {def: 'accountNumber', label: 'accountNumber', hide: false},
    {def: 'createdDate', label: 'createdDate', hide: false},
  ];

  constructor(
    private loadService: LoadService,
    private clientService: ClientService
  ) {}

  ngOnInit(): void {
    this.clientService.getClientsAccounts().subscribe(data => {
      this.createTable(data);
    });
  }

  createTable(data: ClientsAccounts[]) {
    this.dataSource = new MatTableDataSource<ClientsAccounts>(data);
  }

  getDisplayedColumns(){
    return this.columnDefinitions.filter(cd => !cd.hide).map(cd => cd.def);
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
      this.fileName = this.selectedFile.name;
    }
  }

  uploadFile(): void {
    if (!this.selectedFile) return;

    this.loadService.upload(this.selectedFile).subscribe({
      next: (res) => {
        this.clientService.getClientsAccounts().subscribe(data => {
          this.createTable(data);
        })
      },
      error: err => {
        console.error(err);
        alert('Error al subir archivo: '+ err.error.message);
      }
    });

    // Cambia la URL por la de tu servicio backend
    // this.http.post('http://localhost:8080/api/archivo', formData)
    //   .subscribe({
    //     next: (res) => {
    //       console.log('Archivo subido correctamente', res);
    //       alert('Archivo subido correctamente');
    //     },
    //     error: (err) => {
    //       console.error('Error al subir archivo', err);
    //       alert('Error al subir el archivo');
    //     }
    //   });
  }

}
