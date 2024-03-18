// este será o model domain do projeto implementado com uma interface
export interface Employee {
    // estabelecer o conjunto de regras pelas quais os dados serão manipulados dentro da aplicação
    id: number
    name: string
    departamento: string
    dataDeContratacao: Date
    email: string
    phone: string
}
