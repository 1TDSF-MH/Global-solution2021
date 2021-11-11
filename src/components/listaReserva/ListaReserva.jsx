import React from 'react'
import { useEffect, useState } from 'react'
import {FaEdit as ED, FaTrash as EX} from 'react-icons/fa'
import {Link} from 'react-router-dom'
import '../listaProduto/ListaProduto.css'

export default function ListaReserva(){
    const [produtos, setProdutos] = useState([])

    useEffect(()=>{
        fetch("/rest/produto").then((resp) => { return resp.json()
        }).then((resp) => { setProdutos(resp)
        }).catch((error) => console.error(error))
    },[])

    // const handleDelete = (id) => {
    //     fetch("/rest/produto/"+id, {method: 'DELETE'
    //     }).then(()=>{
    //         window.location="/"
    //     }).catch((error) => console.error(error))
    // }

    const tipoProduto = (tipo)=>{
        if(tipo === 1){
            return "Processado"
        } else if(tipo === 0){
            return "In natura"
        }
    }

    const dataAtual = () =>{
        var data = new Date(),
            dia  = data.getDate().toString(),
            diaF = (dia.length == 1) ? '0'+dia : dia,
            mes  = (data.getMonth()+1).toString(),
            mesF = (mes.length == 1) ? '0'+mes : mes,
            anoF = data.getFullYear();
        return diaF+"/"+mesF+"/"+anoF;
    }

    const dataLimite = () =>{
        var data = new Date(),
            dia  = (data.getDate()+3).toString(),
            diaF = (dia.length == 1) ? '0'+dia : dia,
            mes  = (data.getMonth()+1).toString(),
            mesF = (mes.length == 1) ? '0'+mes : mes,
            anoF = data.getFullYear();
        return diaF+"/"+mesF+"/"+anoF;
    }
    
    const [retirar, setRetirar] = useState(false)
    
    let retirarHoje = false

    const tabelaProdutos = (c, i) =>{
        var retirada = ""
        var style = "";
        c.validade.replace("00:00:00", "")
        
        if(c.validade != dataAtual()){
            retirada = dataLimite();

        }else{
            retirada = dataAtual();
            var style = "#421d1d";
            setRetirar(true)

        }
        
        var marca = ""
        if(c.marca !== null){
            marca = c.marca
        } else{
            marca = "S/ marca"
        }
        var lista = 
        <tr  key={i}>
            <td style={{backgroundColor: style}}>{c.codigo}</td>
            <td style={{backgroundColor: style}}>{c.cnpj}</td>
            <td style={{backgroundColor: style}}>{c.nome}</td>
            <td style={{backgroundColor: style}}>{marca}</td>
            <td style={{backgroundColor: style}}>{tipoProduto(c.tipo)}</td>
            <td style={{backgroundColor: style}}>{retirada}</td>
        </tr>

    
        
        return lista;     
        
    }
    
    
    const mensagemRetirada = () => {

        if(retirar){
            return(
                <p>AIIIIIIIIII</p>
            )
        }
    }
   return(
      <div>
        <div>
            {mensagemRetirada()}
        </div>
        <p>Confirmar Reserva # {dataAtual()}</p>
        <table className="tab">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>CNPJ do fornecedor</th>
                    <th>Nome</th>
                    <th>Marca</th>
                    <th>Tipo</th>
                    <th>Prazo para retirada </th>
                </tr>
            </thead>
            <tbody>
                {produtos.map((c,i) => (
                    tabelaProdutos(c, i, c.validade) 
                ))}
            </tbody>
        </table>

        <div className="voteArea">
            <button title="Votar" to="/votacao"  className="votar">Confirmar reserva</button>   
        </div>   

      </div>
)}