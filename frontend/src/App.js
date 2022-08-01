import React, { useEffect, useState }from "react";
import "./App.css";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import {
  HidePlaylistBox,
  ShowPlaylistBox,
} from "./style/styled_component/video_play";

import Index from "./pages/index";
import VideoPlay from "./pages/video_play";
import {useTranslation} from "react-i18next";
import Navigation from "./components/navigation/navigation.jsx";

function App() {
  const [language, setLanguage] = useState(null);

  const { i18n } = useTranslation();

  useEffect(() => {
    if (language === 'ko')
      localStorage.setItem('lang', 'ko');
    else if (language === 'en')
      localStorage.setItem('lang', 'en');
    const lang = localStorage.getItem('lang');
    if (lang === null || lang === 'ko')
      i18n.changeLanguage('ko');
    else
      i18n.changeLanguage('en');
  }, [language, i18n])
  return (
    <>
      <Navigation language={language} setLanguage={setLanguage}/>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Index />} />
          <Route
            path="/video_play"
            element={
              <ShowPlaylistBox>
                <HidePlaylistBox>
                  <VideoPlay/>
                </HidePlaylistBox>
              </ShowPlaylistBox>
            }
          />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;