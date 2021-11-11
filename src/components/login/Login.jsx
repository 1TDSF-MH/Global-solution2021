import React from 'react'
import { useEffect, useState } from 'react'
import { useHistory } from "react-router-dom";
import {MdWarning as W, MdAlternateEmail as M, MdVpnKey as P} from "react-icons/md";

import {Link} from 'react-router-dom'
import './Login.css'

export default function Login(){
    const [usuarios, setUsuarios] = useState([])
    const [ong, setOng] = useState([])
    const [msgErro, setMsgErro] = useState()
    
   
    useEffect(()=>{
        fetch("/rest/usuario").then((resp) => { return resp.json()
        }).then((resp) => { setUsuarios(resp)
        }).catch((error) => console.error(error))
    },[])

    const history = useHistory();
    const verificaSenha = () => {
       
        var email = document.getElementById("email").value;
        var senha = document.getElementById("senha").value;
        
        for(var i = 0; i < usuarios.length; i++){
            if (senha === usuarios[i].senha && email === usuarios[i].email ){
              
                history.push("/formProduto");
                console.log("Acesso Autorizado")
            } else {
              setMsgErro(
                  <div className="msgErro">
                      <W className="warning"/>
                      <p>E-mail ou senha incorretos.</p>
                      <p>Caso tenha esquecido sua senha, entre em contato conosco.</p>
                  </div>
              )
            }
        }
   }
  
   return(
    <div className="geral">
        
        <p className="titulo">Cycle4Food</p> 
        <div className="conteudoLogin">
           <div className="formArea">
               <p>Área do comerciante</p>
               <div className="forme"> <M/><input id="email" className="form" type="email" name="login" placeholder="E-mail" required/> </div>
               <div className="forme"> <P/> <input id="senha" className="form" type="password" name="senha" placeholder="Senha" required/> </div>
               <button className="login" onClick={verificaSenha}>Entrar</button>       
           </div>
           <div className="formArea">
               <p>Área da ONG</p>
               <div className="forme"> <M/><input id="email" className="form" type="email" name="login" placeholder="E-mail" required/> </div>
               <div className="forme"> <P/> <input id="senha" className="form" type="password" name="senha" placeholder="Senha" required/> </div>
               <button className="login" onClick={verificaSenha}>Entrar</button>       
           </div>
        </div>
        <p>Primeira vez por aqui?</p>
        <Link className="cadastrar" title="Cadastre-se" to="/cadastro">Cadastre-se</Link>
         {msgErro}
    </div>
    
)}