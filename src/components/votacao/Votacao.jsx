import React from 'react'
import { useEffect, useState } from 'react'
import './Votacao.css'



export default function Votacao(props){
    const [pratos, setPratos] = useState([])

    useEffect(()=>{
        fetch("/rest/cardapio").then((resp) => { return resp.json()
        }).then((resp) => { setPratos(resp)
        }).catch((error) => console.error(error))
    })

    var prato ={
        codigo: "",
        img: "",
        titulo: "",
        descricao: "",
        preco: "",
        votos: ""
    }

    const handleSubmit = () => {
        fetch("/rest/cardapio/" + prato.codigo,{
            method: 'PUT',
            headers: {
                "Content-type" : "application/json"
            }, body: JSON.stringify(prato)
        }).then(()=>{
            window.location = "/"
        })
    }

    const votar = () => {
        var voto = document.getElementsByName("voto");
       
        for(var i = 0; i< voto.length; i++){
            if(voto[i].checked){
                prato.codigo = i+1
                prato.img = pratos[i].img
                prato.titulo = pratos[i].titulo
                prato.descricao = pratos[i].descricao
                prato.preco = pratos[i].preco
                prato.votos = pratos[i].votos+=1
                console.log(prato)
                handleSubmit()
            }
            
        }
    }
    



   return(
      <div>
          <p className='titulo'>Votar</p>
            <table className="tab">
                <thead>
                    <tr>

                        <th>Código</th>
                        <th>Votos</th>
                        <th>Nome</th>
                        <th>Descrição</th>
                        <th>Preço</th>
                        <th>Votar</th>
                    </tr>
                </thead>
                <tbody>
                    {pratos.map((c,i) => (
                        <tr key={i}>
                            <td>{c.codigo}</td>
                            <td>{c.votos}</td>
                            <td>{c.titulo}</td>
                            <td>{c.descricao}</td>
                            <td>R${c.preco}</td>
                            <td className="inp">
                                <input type="radio" name="voto" value={i+1}/>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="voteArea">
            <button title="Votar" to="/votacao" onClick={votar} className="votar">Votar</button>   
            </div>   
      </div>
)}