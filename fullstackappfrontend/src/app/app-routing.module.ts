import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// importar os componentes
import { HomeComponent } from './componentes/home/home.component';
import { EmployeeCreateComponent } from './componentes/employee-create/employee-create.component';
import { EmployeeEditComponent } from './componentes/employee-edit/employee-edit.component';
import { EmployeeListComponent } from './componentes/employee-list/employee-list.component';
import { EmployeeDetailsComponent } from './componentes/employee-details/employee-details.component';
import { EmployeeDepartmentComponent } from './componentes/employee-department/employee-department.component';
import { EmployeeDepartmentEditComponent } from './componentes/employee-department-edit/employee-department-edit.component';
import { DepartmentListComponent } from './componentes/department-list/department-list.component';

const routes: Routes = [
  // usar o recurso redirectTo para especificar um componente que será carregado quando rotas especificas estiverem ausentes
  // http://localhost:4200/home 
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'employee-create', component: EmployeeCreateComponent},
  // http://localhost:4200/employee-edit/:2
  {path: 'employee-edit/:id', component:EmployeeEditComponent},
  {path: 'employee-list', component: EmployeeListComponent},
  {path: 'employee-details/:id', component: EmployeeDetailsComponent},
  {path: 'employee-department', component: EmployeeDepartmentComponent},
  {path: 'employee-department-edit/:id', component: EmployeeDepartmentEditComponent},
  {path: 'department-list', component: DepartmentListComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
