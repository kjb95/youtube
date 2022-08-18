import React from "react";
import {SelectLanguageSpan} from "../../style/styled_component/navigation";
import {useTranslation} from "react-i18next";

const SelectLanguage = ({language, setLanguage}) => {
  const { t } = useTranslation();
  if (language === null)
    language = localStorage.getItem('lang');

  return <>
    <SelectLanguageSpan currentLanguage={language} language='ko' onClick={() => setLanguage('ko')}>
      {t('korean')}
    </SelectLanguageSpan>
    <SelectLanguageSpan currentLanguage={language} language='en' onClick={() => setLanguage('en')}>
      {t('english')}
    </SelectLanguageSpan>
  </>;
}

export default SelectLanguage;