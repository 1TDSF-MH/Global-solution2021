import React from 'react'
import { useEffect, useState } from 'react'
//import {FaEdit as ED, FaTrash as EX} from 'react-icons/fa'
//import {Link} from 'react-router-dom'
import '../listaProduto/ListaProduto.css'

export default function ListaReserva(){
    const [produtos, setProdutos] = useState([])
    const [reserva, setReserva] = useState([])

    let assocId = window.location.href.substr(window.location.href.length - 4);
    useEffect(()=>{
        fetch("/rest/reserva/produtos/"+assocId).then((resp) => { return resp.json()
        }).then((resp) => { setProdutos(resp)
        }).catch((error) => console.error(error))
    },[])

    useEffect(()=>{
        fetch("/rest/reserva/cdprodutos/"+assocId).then((resp) => { return resp.json()
        }).then((resp) => { setReserva(resp)
        }).catch((error) => console.error(error))
    },[])


    const cancelarReserva = () => {
        fetch("/rest/reserva/delete/"+assocId, {
            method: 'DELETE'
        }).then(()=>{
            window.location="/listaGeral/"+assocId
        }).catch((error) => console.error(error))
    }

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
            diaF = (dia.length === 1) ? '0'+dia : dia,
            mes  = (data.getMonth()+1).toString(),
            mesF = (mes.length === 1) ? '0'+mes : mes,
            anoF = data.getFullYear();
            anoF = anoF.toString().substr(-2)
        return diaF+"/"+mesF+"/"+anoF;
    }

    const dataLimite = () =>{
        var data = new Date(),
            dia  = (data.getDate()+3).toString(),
            diaF = (dia.length === 1) ? '0'+dia : dia,
            mes  = (data.getMonth()+1).toString(),
            mesF = (mes.length === 1) ? '0'+mes : mes,
            anoF = data.getFullYear();
            anoF = anoF.toString().substr(-2)
        return diaF+"/"+mesF+"/"+anoF;
    }

    const tabelaProdutos = (c, i) =>{
        var bgColor = ""
        var retirada = ""
        var quantidade = ""
        for(i =0; i<reserva.length; i++){
            if(reserva[i][0] === c.codigo){
                quantidade = reserva[i][1]
            }
        }



        if(c.validade === dataAtual()) {
           retirada = dataAtual()
           bgColor = "#D86C6C"
        }else{
            retirada = dataLimite()
        }
        var marca = ""
        if(c.marca !== null){
            marca = c.marca
        } else{
            marca = "S/ marca";
        }
        var lista =
        <tr  key={i}>
            <td style = {{backgroundColor : bgColor }}>{c.codigo}</td>
            <td style = {{backgroundColor : bgColor }}>{c.nome}</td>
            <td style = {{backgroundColor : bgColor }}>{marca}</td>
            <td style = {{backgroundColor : bgColor }}>{tipoProduto(c.tipo)}</td>
            <td style = {{backgroundColor : bgColor }}>{quantidade}</td>
            <td style = {{backgroundColor : bgColor }}>{retirada}</td>
        </tr>

        return lista;     
        
    }
    


   return(
      <div>

        <p>Confirmar Reserva # {dataAtual()}</p>
        <table className="tab">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Marca</th>
                    <th>Tipo</th>
                    <th>Quantidade</th>
                    <th>Prazo para retirada </th>
                </tr>
            </thead>
            <tbody>
                {produtos.map((c,i) => (
                    tabelaProdutos(c, i)
                ))}
            </tbody>
        </table>

            <button title="confirmar" className="btn">Confirmar reserva</button>
            <button className="btn" onClick={cancelarReserva}> Cancelar Reserva</button>

      </div>
)}