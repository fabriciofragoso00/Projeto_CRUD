import { Injectable } from '@angular/core';
// importar o model
import { Employee } from '../model/employee';
// importar os recursos para lidar com as requisições http
import { HttpClient, HttpHeaders } from '@angular/common/http';
// importar os recursos para construir as tarefas assincronas descritas no service
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Employeedepartment } from '../model/employee-department';

@Injectable()

export class RestApiService {
  //  1ª parte - definir o "caminho" necessário para que a base de dados possa ser acessada

 apiURL: string = 'http://localhost:8081/api/v1'
  /* 2ª parte -  praticar a referencia de instancia da classe HttpClient para que o objeto referencial gerado possa auxiliar nas requisições
  http que serão realizadas para o acesso a base de dados. Realizando a injecção de dependencia da classe HttpClient injetada no service.
  */

  constructor(private reqHttp: HttpClient) { }
  // 3ª parte  - configurar as credenciais de acesso para que o croos-domain possa funcionar adequadamente

  autorizacaoAcesso = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  }

/*
//========================================================================================
        CONSTRUIR A API E SEUS RESPECTIVOS MÉTODOS E TAREFAS ASSINCRONAS
//========================================================================================
*/

 lerDadosColab(): Observable<Employee>{
 // Qual o retorno do método? 
 // Resposta: API get() http://localhost:3000/employees
  return this.reqHttp.get<Employee>(this.apiURL+'/employees')
  .pipe(
    /* este .pipe() estabelece um canal direto de comunicação entre a aplicação front-end e a aplicação back-end.
      De maneira precisa, estabelece  um "duto"  direto de comunicação entre o método lerDadosColab() com a base de dados
      employee. Este método tem origem NodeJS.
    */
    retry(1),
    catchError(this.tratarErro)
  )
 }

 /* 2ª tarefa assincrona: criar um método/função/requisição HTTP para que seja acessada um  unico registro da base desde que
 seja devidamente identificada.
 */
 acessarUmRegistro(id:any): Observable<Employee>{
  // API get() http://localhost3000/employee/:1
  return this.reqHttp.get<Employee>(this.apiURL+'/employees/'+id, this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 // 3ª tarefa assincrona: criar um método/função/requisição HTTP para que a base seja acessada e,  dentro da base, seja inserido um novo registro
 inserirDados(dadosRecebidos: any): Observable<Employee>{
  return this.reqHttp.post<Employee>(this.apiURL+'/employees', JSON.stringify(dadosRecebidos),this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 // 4ª tarefa assincrona: criar um método/função/requisição para atualização/alteração de um registro armazenado na base e, posteriormente, rearmazena-lo.
 alterarDados(id:any, novosDados:any): Observable<Employee>{
  return this.reqHttp.put<Employee>(this.apiURL+'/employees/'+id, JSON.stringify(novosDados), this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 /* 5ª tarefa assincrona: criar um método/função/requisição para exclusão de um registro. Para excluir um registro é necessário que o registro
  esteja armazenado e devidamente identificado.
 */
 excluirDados(id: any){
  return this.reqHttp.delete<Employee>(this.apiURL+'/employees/'+id, this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 // 4ª parte - definir uma função de tratamento de erros que auxiliará na investigação de onde  os erros estão ocorrendo.
 tratarErro(erro:any){
  // definir uma propriedade para receber o valor "informação" a respeito de onde e qual é a ocorrencia de erro.
 let mensagemErro: any = '' 
 // criar uma estrutura de verificação para saber em qual "pedaço"  da aplicação o erro está ocorrendo
  if(erro.error instanceof ErrorEvent){
    // tratar o erro, se ele ocorrer no front-end
    mensagemErro = erro.error.message
  }else{
    // tratar o erro, se ele ocorrer no back-end simulado
    mensagemErro = `Codigo do erro: ${erro.status}\nMensagem do erro é: ${erro.message}`
  }
  // exibir o erro numa janela de alerta
  alert(mensagemErro)
  return throwError(() => mensagemErro)
 }

 /*
 //===========================================================================================
                                    C R U D E                            
 //============================================================================================
 */

 lerDepartment(): Observable<Employeedepartment>{
  return this.reqHttp.get<Employeedepartment>(this.apiURL+'/employeesDepartment')
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 acessarUmDepartment(id:any): Observable<Employeedepartment>{
  return this.reqHttp.get<Employeedepartment>(this.apiURL+'/employeesDepartment/'+id, this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 inserirDepartment(dadosRecebidos: any): Observable<Employeedepartment>{
  return this.reqHttp.post<Employeedepartment>(this.apiURL+'/employeesDepartment', JSON.stringify(dadosRecebidos),this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 alterarDepartment(id:any, novosDados:any): Observable<Employeedepartment>{
  return this.reqHttp.put<Employeedepartment>(this.apiURL+'/employeesDepartment/'+id, JSON.stringify(novosDados), this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }

 excluirDepartment(id: any){
  return this.reqHttp.delete<Employeedepartment>(this.apiURL+'/employeesDepartment/'+id, this.autorizacaoAcesso)
  .pipe(
    retry(1),
    catchError(this.tratarErro)
  )
 }
 
}
  