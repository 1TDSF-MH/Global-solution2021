import React from 'react'
import './FormProduto.css'
import {useEffect, useState} from 'react'
import {Link} from 'react-router-dom'

export default function FormProduto() {
    let userCode = window.location.href.substr(window.location.href.length - 3);
    const dataAtual = () => {
        var data = new Date(),
            dia = data.getDate().toString(),
            diaF = (dia.length === 1) ? '0' + dia : dia,
            mes = (data.getMonth() + 1).toString(),
            mesF = (mes.length === 1) ? '0' + mes : mes,
            anoF = data.getYear() - 100;
        return diaF + "/" + mesF + "/" + anoF;
    }
    const [produtoCarregado, setProdutoCarregado] = useState(false)
    const [produto, setProduto] = useState({
        cdEstab: userCode,
        nome: "",
        tipo: "",
        marca: "",
        validade: "",
        dataEntrada: dataAtual(),
        estoque: ""
    })
 
    useEffect(() => {
        const formataData = (data) => {
            return data.replace("00:00:00", "").replace(" ", "")
        }
        var cdProduto = window.location.href.substr(window.location.href.length - 5);
        if (cdProduto && window.location.href.length === 39) {
            fetch("/rest/produto/" + cdProduto).then(resp => {
                return resp.json()
            }).then(data => {
                data.validade = formataData(data.validade)
                data.dataEntrada = formataData(data.dataEntrada)
                setProduto(data)
                setProdutoCarregado(true)
            })
        }
    }, [])

    const handleChange = (e) => {
        setProduto({...produto, [e.target.name]: e.target.value })
    }

    const verificaChecked = () => {
        if (produto.tipo === 0) {
            return (
                <div className="radioArea">
                    <input value="0"  checked name="tipo" type="radio" onChange={handleChange}/>
                    <p>In Natura</p>
                    <input value="1" name="tipo" type="radio" onChange={handleChange}/>
                    <p>Processado</p>
                </div>
            )
        } else if (produto.tipo === 1) {
            return (
                <div className="radioArea">
                    <input value="0" name="tipo" type="radio" onChange={handleChange}/>
                    <p>In Natura</p>
                    <input value="1" checked name="tipo" type="radio" onChange={handleChange}/>
                    <p>Processado</p>
                </div>
            )
        } else {
            return (
                <div className="radioArea">
                    <input value="0" name="tipo" type="radio" onChange={handleChange}/>
                    <p>In Natura</p>
                    <input value="1" name="tipo" type="radio" onChange={handleChange}/>
                    <p>Processado</p>
                </div>
            )
        }
    }

    const cadastrarProduto = () => {
        fetch("/rest/produto", {
            method: 'POST',
            headers: {
                "Content-type" : "application/json"
            }, body: JSON.stringify(produto)
        }).then(()=>{
            window.location="/meusProdutos/"+produto.cdEstab
        })
    }

    const deletarProduto = () => {
        fetch("/rest/produto/delete/"+produto.codigo, {
            method: 'DELETE'
        }).then(()=>{
            window.location="/meusProdutos/"+produto.cdEstab
        }).catch((error) => console.error(error))
    }

    const editarProduto = () => {
        fetch("/rest/produto/update/"+produto.codigo, {
            method: 'PUT',
            headers: {
                "Content-type" : "application/json"
            }, body: JSON.stringify(produto)
        }).then(()=>{
            window.location="/meusProdutos/"+produto.cdEstab
        })
    }
    
    const mostrarBotoes = () => {
        var btn1 =
        <button className="formButton" onClick={cadastrarProduto}>Cadastrar Produto</button>
        var btn2 =  <>
                        <button className="formButton" onClick={editarProduto}>Editar Produto</button>
                        <button className="formButton" onClick={deletarProduto}>Excluir Produto</button>
                    </>
        if(produtoCarregado){
            return btn2
        } else{
            return btn1;
        }
    }

    return (
        <div>
            <div className="regArea">
                <p className="tituloC">Meu Produto</p>
                <p className="subT">Nome</p>
                <input type="text" defaultValue={produto.nome} onChange={handleChange} placeholder="Nome do produto" className="formProd"
                       id="nomeProduto" name="nome"/>
                <p className="subT">Marca (opcional)</p>
                <input type="text" defaultValue={produto.marca} onChange={handleChange} placeholder="Marca do produto"  className="formProd"
                       id="marcaProduto" name="marca"/>
                <p className="subT">Validade</p>
                <input type="date" defaultValue={produto.validade} onChange={handleChange} className="formProd" id="validadeProduto" name="validade"/>
                <p className="subT">Tipo</p>
                {verificaChecked()}
                <p className="subT">Quantidade</p>
                <input type="number" defaultValue={produto.estoque} onChange={handleChange} className="formProd" min="1" id="qtdProduto" name="estoque"/>
                {mostrarBotoes()}
                <div>
                    <Link title="Home" to={"/meusProdutos/"+produto.cdEstab} className="produtosBtn">Meus Produtos</Link>
                </div>
            </div>
        </div>
    )
}