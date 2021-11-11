import React from 'react'
import {Switch, Route, Link} from 'react-router-dom'
import logo from '../img/Logo.png'
import './Container.css'
import {FaRebel as Rebel, FaPiedPiperAlt as Pied, FaEmpire as Empire, FaPlaystation as Playstation} from 'react-icons/fa'
import { GiTriforce as Triforce } from "react-icons/gi";



import ListaProdutos from '../listaProduto/ListaProdutos'
import ListaReserva from '../listaReserva/ListaReserva'
import FormProduto from '../formProduto/FormProduto'
import Cadastro from '../cadastro/Cadastro'
import Login from '../login/Login'
import Home from '../home/Home'
export default function Container(){

   return(
    <div>
        <div className="cabecalho">
            <img src={logo} className="logo"/>
            <Link title="Home" to="/" className="cText">Home</Link>
            <Link title="Lista de reserva" to="/listaReserva" className="cText">Lista de Reserva</Link>
        </div>
        <div className="conteudo">
            <Switch >
                <Route path="/" exact component={Home}/>
                <Route path="/login" component={Login}/>
                <Route path="/listaGeral" component={ListaProdutos}/>
                <Route path="/listaReserva" component={ListaReserva}/>
                <Route path="/formProduto" component={FormProduto}/>
                <Route path="/cadastro" component={Cadastro}/>
            </Switch>
        </div>
           
        {/* <footer>
            <div className="rodape">
                <div class="integ">
                    <p><Triforce/> Caio</p>
                    <p><Empire/> Isabela</p>
                    <p><Pied/> Jonatan</p>
                    <p><Rebel/> Lucas</p>
                    <p><Playstation/> Rodrigo</p>
                </div>
            </div>
        </footer> */}
    </div>
)}