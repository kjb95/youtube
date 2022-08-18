import React from "react";
import {LoginButtonSpan} from "../../style/styled_component/navigation.jsx";
import {useTranslation} from "react-i18next";

function LoginButton() {
  const { t } = useTranslation();

  return <a href='/login'>
    <LoginButtonSpan>{t('login')}</LoginButtonSpan>
  </a>
}

export default LoginButton;