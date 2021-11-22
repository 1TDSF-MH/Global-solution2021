import React from "react";
import { Switch, Route, Link} from "react-router-dom";
import logo from "../img/c4flogo.png";
import "./Container.css";
import ListaEmpresa from "../listaEmpresa/ListaEmpresa";
import ListaProdutos from "../listaProduto/ListaProdutos";
import ListaReserva from "../listaReserva/ListaReserva";
import FormProduto from "../formProduto/FormProduto";
import Cadastro from "../cadastro/Cadastro";
import Login from "../login/Login";
import Home from "../home/Home";
import ResumoReserva from "../resumoReserva/ResumoReserva";

export default function Container() {
  return (
    <div>
      <div className="cabecalho">
        <Link className="linkCont" to="/">
          <img src={logo} className="logo" alt="Logotipo Cycle4Food" />
        </Link>
        <Link title="Home" to="/" className="cText">
          Home
        </Link>
      </div>
      <div className="conteudo">
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/login" component={Login} />
          <Route path="/listaGeral" component={ListaProdutos} />
          <Route path="/listaReserva" component={ListaReserva} />
          <Route path="/formProduto" component={FormProduto} />
          <Route path="/cadastro" component={Cadastro} />
          <Route path="/meusProdutos" component={ListaEmpresa} />
          <Route path="/resumo" component={ResumoReserva} />
        </Switch>
      </div>
    </div>
  );
}
