import React from 'react';
import {PlaylistRandomButtonImg} from '../../style/styled_component/videoPlay.jsx';
import {randomPlay} from '../../service/videPlay/randomButton.jsx';

const RandomButton = ({
	playlistDataUpdate,
	setPlaylistUpdate
}) => {
	const isRandom = window.localStorage.getItem('isRandom');

	return <PlaylistRandomButtonImg src='./img/randomButton.png' alt='randomButton' onClick={event => {
		randomPlay(event, playlistDataUpdate, setPlaylistUpdate);
	}} isRandom={isRandom} />
}

export default RandomButton;
