import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'lanchonete' },
  {
    path: 'lanchonete',
    loadChildren: () =>
      import('./lanchonete/lanchonete.module').then((m) => m.LanchoneteModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
