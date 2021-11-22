import React from "react";
import { Link } from "react-router-dom";
import "./Home.css";
import titleImg from "../img/cf4title.png";
import img1 from "../img/img1.gif";
import img2 from "../img/img2.gif";
import img3 from "../img/img3.gif";

import SimpleImageSlider from "react-simple-image-slider";

export default function Home() {
  const images = [{ url: img1 }, { url: img2 }, { url: img3 }];

  return (
    <div className="geral">
      <SimpleImageSlider
        width={900}
        height={200}
        images={images}
        autoPlayDelay={5} // tempo em segundos p/ trocar de img
        autoPlay={true}
        loop={true}
        slideDuration={1} // tempo da velocidade do slide
      />
      <div className="divImg">
        <img src={titleImg} className="titulo" alt="" />
      </div>
      <Link className="cadastrar2" title="Cadastre-se" to="/cadastro">
        Cadastre-se
      </Link>

      <Link className="cadastrar2" title="Cadastre-se" to="/login">
        Entrar
      </Link>
    </div>
  );
}
