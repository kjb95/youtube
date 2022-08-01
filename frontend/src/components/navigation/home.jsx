import React from "react";
import {HomeSpan} from "../../style/styled_component/navigation";
import {useTranslation} from "react-i18next";

function Home() {
  const { t } = useTranslation();

  return <a href='/'>
    <HomeSpan>
      {t('home')}
    </HomeSpan>;
  </a>;
}

export default Home;