import React from "react";
import { useEffect, useState } from "react";
import "./ListaProduto.css";

export default function ListaProdutos() {
  const [produtos, setProdutos] = useState([]);

  useEffect(() => {
    fetch("/rest/produto")
      .then((resp) => {
        return resp.json();
      })
      .then((resp) => {
        setProdutos(resp);
      })
      .catch((error) => console.error(error));
  }, []);

  const tipoProduto = (tipo) => {
    if (tipo === 1) {
      return "Processado";
    } else if (tipo === 0) {
      return "In natura";
    }
  };

  const dataAtual = () => {
    var data = new Date(),
      dia = data.getDate().toString(),
      diaF = dia.length === 1 ? "0" + dia : dia,
      mes = (data.getMonth() + 1).toString(),
      mesF = mes.length === 1 ? "0" + mes : mes,
      anoF = data.getFullYear();
    return diaF + "/" + mesF + "/" + anoF;
  };

  const tabelaProdutos = (c, i, prioridade) => {
    var formatVal1 = c.validade.replace("00:00:00", "");
    var formatVal2 = formatVal1.split("-");
    var formatVal3 = formatVal2[2] + "/" + formatVal2[1] + "/" + formatVal2[0];
    var validade = formatVal3.replace(" ", "");

    var marca = "";
    if (c.marca !== null) {
      marca = c.marca;
    } else {
      marca = "S. marca / Fab. própria";
    }

    var lista = (
      <tr key={i}>
        <td>{c.codigo}</td>
        <td className="nomeProd">{c.nome}</td>
        <td className="marcaProduto">{marca}</td>
        <td className="tipoProduto">{tipoProduto(c.tipo)}</td>
        <td>{validade}</td>
        <td>{c.estoque}</td>
        <td id={c.codigo}>
          <input id={c.codigo} type="checkbox" name="select" />
          <input
            className="numeroProd"
            type="number"
            name="value"
            id={c.codigo}
            placeholder="quantidade"
            min="1"
            max={c.estoque}
          />
        </td>
      </tr>
    );

    if (validade === dataAtual() && prioridade === 1) {
      return lista;
    } else if (validade !== dataAtual() && prioridade !== 1) {
      return lista;
    }
  };

  const selecionarProdutos = () => {
    var cdOng = window.location.href.substr(window.location.href.length - 4);
    var lista = [[cdOng, ""]];
    var campoVazio = true;
    var select = document.getElementsByName("select");
    var valor = document.getElementsByName("value");

    for (var i = 0; i < select.length; i++) {
      if (select[i].checked) {
        var id = select[i].id;
        var qtd = valor[i].value;

        if (qtd === "") {
          document.getElementById(id).style.backgroundColor = "#4F0000";
          campoVazio = false;
        } else {
          document.getElementById(id).style.backgroundColor = "#3B4044";
          lista.push([id, qtd]);
        }
      }
    }

    if (campoVazio) {
      fetch("/rest/reserva", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify(lista),
      }).then(() => {
        window.location = "/listaReserva/" + cdOng;
      });
    }
  };

  return (
    <div>
      <p className="subT" style={{ margin: 0 }}>
        Alimentos vencendo hoje {dataAtual()}
      </p>
      <table className="tab" style={{ marginLeft: 0, marginBottom: 50 }}>
        <thead>
          <tr>
            <th>Código do produto</th>
            <th>Nome</th>
            <th>Marca</th>
            <th>Tipo</th>
            <th>Validade</th>
            <th>Estoque</th>
            <th>Selecionar</th>
          </tr>
        </thead>
        <tbody>{produtos.map((c, i) => tabelaProdutos(c, i, 1))}</tbody>
      </table>

      <p className="subT" style={{ margin: 0 }}>
        Alimentos com data de validade maior
      </p>
      <table className="tab" style={{ marginLeft: 0 }}>
        <thead>
          <tr>
            <th>Código do produto</th>
            <th>Nome</th>
            <th>Marca</th>
            <th>Tipo</th>
            <th>Validade</th>
            <th>Estoque</th>
            <th>selecionar</th>
          </tr>
        </thead>
        <tbody>{produtos.map((c, i) => tabelaProdutos(c, i, 2))}</tbody>
      </table>
      <div>
        <button title="reserva" onClick={selecionarProdutos} className="btn">
          Selecionar produtos
        </button>
      </div>
    </div>
  );
}
