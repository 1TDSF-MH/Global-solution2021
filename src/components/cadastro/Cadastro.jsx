import React from 'react'
import {useEffect, useState} from 'react'
import "./Cadastro.css"
import {cnpj} from 'cpf-cnpj-validator';
import validator from "validator";


/*
    INSTALAÇÕES NECESSÁRIAS:
    npm install validator
    npm i cpf-cnpj-validator -S
*/
export default function Cadastro() {
    const [formulario, setFormulario] = useState()



    const cadastroEmpresa = () => {

        var cnpjE = document.getElementById("cnpjEmpresa").value;
        var nome = document.getElementById("nomeEmpresa").value;
        var cep = document.getElementById("cepEmpresa").value;
        var endereco = document.getElementById("enderecoEmpresa").value;
        var telefone = document.getElementById("telefoneEmpresa").value;
        var email = document.getElementById("emailEmpresa").value;
        var senha = document.getElementById("senhaEmpresa").value;
        var tipoList = document.getElementsByName("tipoEstabelecimento");
        var tipo = "";

        for(var i = 0; i <tipoList.length; i++){
            if(tipoList[i].checked){
                tipo = tipoList[i].value;
                break;
            }
        }
        // exemplo cnpj valido: 58.403.919/0001-06
        var cadastro = {
            cnpj: cnpjE,
            nome: nome,
            cep: cep,
            endereco: endereco,
            telefone: telefone,
            email: email,
            senha: senha,
            tipo: tipo
        }

        var cadastroValido = true
        for (var key in cadastro) {
            if (cadastro.hasOwnProperty(key)) {
                var val = cadastro[key];
                if(val === ""){
                    cadastroValido = false
                    break;
                }
            }
        }

        const handleSubmit = () => {

            fetch("rest/usuario",{
                method: "POST",
                headers: {
                    "Content-type" : "application/json"
                }, body: JSON.stringify(cadastro)
            }).then(()=>{
                window.location = "/"
            })
        }
        console.log(cadastro.senha.length)

        cnpjE = cnpj.strip(cnpjE)
        if(cnpj.isValid(cnpjE) && validator.isEmail(email) && cadastroValido && cadastro.senha.length <= 6){
           console.log("OK")

            handleSubmit()

        }
    }


    /*======= VERIFICAÇÕES =======*/
    const verificaCnpj = () => {
        var cnpjE = document.getElementById("cnpjEmpresa").value;
        cnpjE = cnpj.strip(cnpjE)

        if(cnpj.isValid(cnpjE)){
            document.getElementById("cnpjEmpresa").style.backgroundColor="#ffffff";
        }else{
            document.getElementById("cnpjEmpresa").style.backgroundColor="#E57373";
        }
    }

    const verificaEmail = () =>{
        var validator = require('validator');
        var emailE = document.getElementById("emailEmpresa").value;
        if(validator.isEmail(emailE)){
            document.getElementById("emailEmpresa").style.backgroundColor="#ffffff"
        } else {
            document.getElementById("emailEmpresa").style.backgroundColor="#E57373";
        }
    }

    const verificaSenha = () => {
        var senhaE = document.getElementById("senhaEmpresa").value;
        if(senhaE.length > 6){
            document.getElementById("senhaEmpresa").style.backgroundColor="#E57373";
        } else {
            document.getElementById("senhaEmpresa").style.backgroundColor="#ffffff";
        }

    }

    /*======= FORMULARIOS =======*/
    const formEntidade = () => {
        setFormulario(
            <div>
                <p className="text">Se você deseja retirar produtos, faça o cadastro de sua ONG.</p>
                <div className="formArea" style={{marginLeft: 0}}>
                    <input id="cnpjOng"className="formCadastro" type="text" placeholder="cnpj"/>
                    <input className="formCadastro" type="text" placeholder="nome da entidade"/>
                    <input className="formCadastro" type="text" placeholder="cep"/>
                    <input className="formCadastro" type="text" placeholder="endereço"/>
                    <input className="formCadastro" type="text" placeholder="número de telefone"/>
                    <input className="formCadastro" type="text" placeholder="endereço de e-mail"/>
                    <input className="formCadastro" type="password" placeholder="senha (até 6 dígitos)"/>
                    <input className="formCadastro" type="text" placeholder="nome do responsável"/>
                    <button className="formbutton">Cadastrar</button>
                </div>
            </div>
        )
    }

    const formEmpresa = () => {
        setFormulario(
            <div>
                <p className="text">Se você deseja cadastrar produtos para que sejam retirados por ONGs, faça o cadastro
                    do seu estabelecimento.</p>
                <div className="formArea" style={{marginLeft: 0}}>
                    <input id="cnpjEmpresa" className="formCadastro" type="text" placeholder="cnpj" onChange={verificaCnpj}/>
                    <input id="nomeEmpresa" className="formCadastro" type="text" placeholder="nome da empresa"/>
                    <input id="cepEmpresa" className="formCadastro" type="text" placeholder="cep"/>
                    <input id="enderecoEmpresa" className="formCadastro" type="text" placeholder="endereço"/>
                    <input id="telefoneEmpresa" className="formCadastro" type="text" placeholder="número de telefone"/>
                    <input id="emailEmpresa" className="formCadastro" type="text" placeholder="endereço de e-mail" onChange={verificaEmail}/>
                    <input id="senhaEmpresa" className="formCadastro" type="password" placeholder="senha (até 6 dígitos)" onChange={verificaSenha}/>
                    <p style={{marginBottom: 0}}>Tipo de estabelecimento</p>
                    <div className="tipoArea">
                        <input value="0" name="tipoEstabelecimento" className="radio" type="radio" placeholder="nome"/>
                        Supermercado
                    </div>
                    <div className="tipoArea">
                        <input value="1" name="tipoEstabelecimento" className="radio" type="radio" placeholder="nome"/>
                        Restaurante / Padaria / Café
                    </div>

                    <button className="formbutton" onClick={cadastroEmpresa}>Cadastrar</button>
                </div>
            </div>
        )
    }

    return (
        <div>
            <p className='tituloC'>
                CADASTRE-SE
            </p>
            <div className="btnArea">
                <button onClick={formEntidade} className="btn1">ONG</button>
                <button onClick={formEmpresa} className="btn2">Empresa</button>
            </div>
            {formulario}
        </div>
    )
}