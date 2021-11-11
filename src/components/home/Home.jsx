import React from 'react'
import {Link} from 'react-router-dom'
import './Home.css'

export default function Home (){

   return(
      <div className="geral">
        <p className="titulo">Cycle4Food</p> 
        <p>Primeira vez por aqui?</p>
        <Link className="cadastrar" title="Cadastre-se" to="/cadastro">Cadastre-se</Link>
        <Link className="cadastrar" title="Cadastre-se" to="/login">Entrar</Link>
    </div>
)}