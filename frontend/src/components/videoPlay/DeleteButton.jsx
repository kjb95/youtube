import React from 'react';
import {PlaylistModifyButtonImg} from '../../style/styledComponent/videoPlay.jsx';
import {deletePlayList} from '../../service/videPlay/deleteButton.jsx';

const DeleteButton = ({
	checkboxes,
	playlistDataUpdate,
	setPlaylistUpdate
}) => {
	return (
			<PlaylistModifyButtonImg src='./img/deleteButton.png' alt='deleteButton' onClick={() => {
				deletePlayList(checkboxes, playlistDataUpdate, setPlaylistUpdate);
			}} />);
};

export default DeleteButton;
