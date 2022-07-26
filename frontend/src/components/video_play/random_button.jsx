import React from 'react';
import { PlaylistRandomButtonImg } from '../../style/styled_component/video_play';
import { randomPlay } from '../../service/vide_play/random_button';

const RandomButton = ({playlistDataUpdate, setPlaylistUpdate}) => {
  const isRandom = window.localStorage.getItem('isRandom');

  return <PlaylistRandomButtonImg
    src='./img/randomButton.png'
    alt='randomButton'
    onClick={ event => { randomPlay(event, playlistDataUpdate, setPlaylistUpdate); }}
    isRandom = {isRandom}
  />
}

export default RandomButton;
