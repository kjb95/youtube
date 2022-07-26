import React, { useState, useEffect } from "react";
import axios from 'axios';

import "../style/css/common.css";

import { IndexVideoMain } from "../style/styled_component/index";
import IndexVideoElement from "../components/index/index_video_element";

const Index = () => {
  const [playlist, setPlaylist] = useState(undefined);

  useEffect(() => {
     // window.localStorage.clear();
    axios.get('http://localhost:8080/api/playlist')
      .then(res => setPlaylist(res.data));
  }, []);

  if (!playlist) return "";

  return (
    <IndexVideoMain>
      {playlist.map((playlist) => {
        if (playlist.exist !== true)
          return '';
        return <li key={playlist.id}>
          <IndexVideoElement
            id={playlist.id}
            title={playlist.title}
            channelTitle={playlist.channelTitle}
            viewCount={playlist.viewCount}
            publishedAt={playlist.publishedAt}
          />
        </li>
      })}
    </IndexVideoMain>
  );
};

export default Index;
