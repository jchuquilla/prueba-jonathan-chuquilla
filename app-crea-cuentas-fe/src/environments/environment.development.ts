export const environment = {
  PREFIJO: 'http',
  DOMINIO: 'localhost:8080',
  CONTEXTO: 'app-crea-cuentas-be/api',
  get HOST(){
    return `${this.PREFIJO}://${this.DOMINIO}/${this.CONTEXTO}`;
  }
};
