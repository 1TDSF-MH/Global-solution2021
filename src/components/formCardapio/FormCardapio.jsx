import React from 'react'
import { useEffect, useState } from 'react'
import {Link} from 'react-router-dom'
import './FormCardapio.css'
export default function FormCardapio(props){

    let id = null
    if (props.match.path.toLowerCase().includes('editar')) {
        id = props.match.params.id
    }

    const [novo, setNovo] = useState({
        codigo: id,
        titulo: "",
        descricao: "",
        preco: "",
        votos: "",
    })

    let metodo = ''
    metodo = id != null ? 'PUT' : 'POST'

    const handleChange = (e) => {
        setNovo({...novo, [e.target.name]: e.target.value })
    }


    const handleSubmit = (e) => {
        e.preventDefault()

        fetch("/rest/cardapio/" + (id ? id : ""),{
            method: metodo,
            headers: {
                "Content-type" : "application/json"
            }, body: JSON.stringify(novo)
        }).then(()=>{
            window.location = "/"
        })
    }

    useEffect(() => {
        if(id){
            fetch("/rest/cardapio/" +id).then(resp=>{
                return resp.json()
            }).then(data=>{
                setNovo(data)
            })
        }
    },[id])


    return (
        <div>
            <p className="titulo">Inserir Prato</p>
            <div className="formArea">
                
                <div>
                    <div>
                        <input className="nome" type="text" name="titulo" placeholder="Nome" value={novo.titulo} onChange={handleChange} />
                    </div>
                    <div>
                        <textarea className="desc" name="descricao" placeholder="Descrição" value={novo.descricao} onChange={handleChange} />
                    </div>
                </div>

                <div className="right">
                    <input className="preco" type="number" name="preco" placeholder="Preço" value={novo.preco} onChange={handleChange} />
                    <button className="enviar" onClick={handleSubmit}>ENVIAR</button>
                    <Link className="cancelar" title="CANCELAR" to="/">CANCELAR</Link>
                </div>
            </div>
        </div>
    )
}
