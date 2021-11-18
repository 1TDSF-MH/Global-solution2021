import ReactDOM from 'react-dom';
import App from './App';
import {BrowserRouter} from 'react-router-dom';

ReactDOM.render(
  <BrowserRouter>
  <App/>
  </BrowserRouter>, document.getElementById('root')
);

/*
INSTALAÇÕES NECESSÁRIAS:
npm install validator
npm i cpf-cnpj-validator -S

*/