import React from 'react'
import {Link} from 'react-router-dom'
import './Home.css'
import titleImg from '../img/cf4title.png'
export default function Home (){

   return(
      <div className="geral">

        <img src={titleImg} className="titulo" alt=""/>

        <Link className="cadastrar2" title="Cadastre-se" to="/cadastro">Cadastre-se</Link>

        <Link className="cadastrar2" title="Cadastre-se" to="/login">Entrar</Link>

    </div>
)}