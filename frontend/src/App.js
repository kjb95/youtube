import React from "react";
import "./App.css";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import {
  HidePlaylistBox,
  ShowPlaylistBox,
} from "./style/styled_component/video_play";

import Index from "./pages/index";
import VideoPlay from "./pages/video_play";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Index />} />
        <Route
          path="/video_play"
          element={
            <ShowPlaylistBox>
              <HidePlaylistBox>
                <VideoPlay />
              </HidePlaylistBox>
            </ShowPlaylistBox>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;