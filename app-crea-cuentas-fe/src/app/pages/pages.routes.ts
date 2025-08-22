import {Routes} from '@angular/router';
import {LoadClientsComponent} from './load-clients/load-clients.component';

export const pagesRoutes: Routes = [
  { path: '', redirectTo: 'load', pathMatch: 'full' },
  { path: 'load', component: LoadClientsComponent },
];
