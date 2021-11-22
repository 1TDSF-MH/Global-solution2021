import React from "react";
import { useEffect, useState } from "react";
import { BiEditAlt as Edit } from "react-icons/bi";
import { RiDeleteBinLine as Del } from "react-icons/ri";
import "./ListaEmpresa.css";
import { useHistory } from "react-router-dom";

export default function ListaProdutos() {
  const [produtos, setProdutos] = useState([]);
  const history = useHistory();

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

  var loggedUser = window.location.href.substr(window.location.href.length - 3);

  const tabelaProdutos = (c, i) => {
    if (loggedUser === c.cdEstab) {
      const toEditPg = () => {
        history.push("/formProduto/" + c.codigo);
      };

      var formatVal1 = c.validade.replace("00:00:00", "");
      var formatVal2 = formatVal1.split("-");
      var formatVal3 =
        formatVal2[2] + "/" + formatVal2[1] + "/" + formatVal2[0];
      var validade = formatVal3.replace(" ", "");

      var marca = "";
      if (c.marca !== null) {
        marca = c.marca;
      } else {
        marca = "S/ marca";
      }
      var lista = (
        <tr key={i}>
          <td>{c.codigo}</td>
          <td className="nomeProd">{c.nome}</td>
          <td className="marcaProd">{marca}</td>
          <td className="tipoProd">{tipoProduto(c.tipo)}</td>
          <td>{validade}</td>
          <td>{c.estoque}</td>

          <td className="selectBtn" id={c.codigo} onClick={toEditPg}>
            <p style={{ margin: 0, textAlign: "center" }}>
              <Edit /> / <Del />
            </p>
          </td>
        </tr>
      );
      return lista;
    }
  };

  return (
    <div>
      <p className="subT" style={{ margin: 0 }}>
        Meus Produtos
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
            <th>Editar / Excluir</th>
          </tr>
        </thead>
        <tbody>{produtos.map((c, i) => tabelaProdutos(c, i, 2))}</tbody>
      </table>
    </div>
  );
}
