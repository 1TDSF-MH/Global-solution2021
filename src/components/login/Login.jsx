import React from 'react'
import {useState} from 'react'
import { useHistory } from "react-router-dom";
import {MdWarning as W, MdAlternateEmail as M, MdVpnKey as P} from "react-icons/md";
import {Link} from 'react-router-dom'
import './Login.css'

export default function Login(){
    const [msgErro, setMsgErro] = useState();
    const history = useHistory();
    
    const verificaSenhaOng = () =>{
        fetch("/rest/associacao/login/"+document.getElementById("emailOng").value+"/"+document.getElementById("senhaOng").value).then((resp) => { return resp.json()
        }).then(resp => {
            if(resp){
                fetch("/rest/associacao/codigo/"+document.getElementById("emailOng").value).then((resp2) => { return resp2.json()
                }).then(resp2 => {
                    history.push("/listaGeral/"+resp2);
                }).catch((error) => console.error(error))
                console.log("Acesso Autorizado")
            } else {
                setMsgErro(
                    <div className="msgErro">
                        <W className="warning"/>
                        <p>E-mail ou senha incorretos.</p>
                        <p>Caso tenha esquecido sua senha, entre em contato conosco.</p>
                    </div>
                )
                console.log("Acesso Negado")
            }
        }).catch((error) => console.error(error))
    }
    
    const verificaSenhaEmp = () =>{
        fetch("/rest/usuario/login/"+document.getElementById("email").value+"/"+document.getElementById("senha").value).then((resp) => { return resp.json()
        }).then(resp => {
            if(resp){
                fetch("/rest/usuario/codigo/"+document.getElementById("email").value).then((resp2) => { return resp2.json()
                }).then(resp2 => {
                    history.push("/formProduto/"+resp2);
                }).catch((error) => console.error(error))
                console.log("Acesso Autorizado")
            } else {
                setMsgErro(
                    <div className="msgErro">
                        <W className="warning"/>
                        <p>E-mail ou senha incorretos.</p>
                        <p>Caso tenha esquecido sua senha, entre em contato conosco.</p>
                    </div>
                )
                console.log("Acesso Negado")
            }
        }).catch((error) => console.error(error))
    }
  
   return(
    <div className="geral">
        
        <div className="conteudoLogin">
           <div className="formArea">
               <p>Área do comerciante</p>
               <div className="forme"> <M/><input id="email" className="form" type="email" name="login" placeholder="E-mail" required/> </div>
               <div className="forme"> <P/> <input id="senha" className="form" type="password" name="senha" placeholder="Senha" required/> </div>
               <button className="login" onClick={verificaSenhaEmp}>Entrar</button>       
           </div>
           <div className="formArea">
               <p>Área da ONG</p>
               <div className="forme"> <M/><input id="emailOng" className="form" type="email" name="login" placeholder="E-mail" required/> </div>
               <div className="forme"> <P/> <input id="senhaOng" className="form" type="password" name="senha" placeholder="Senha" required/> </div>
               <button className="login" onClick={verificaSenhaOng}>Entrar</button>       
           </div>
        </div>
        <p>Primeira vez por aqui?</p>
        <Link className="cadastrar" title="Cadastre-se" to="/cadastro">Cadastre-se</Link>
        {msgErro}
    </div> 
    
)}