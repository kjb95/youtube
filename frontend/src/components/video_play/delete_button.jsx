import React from "react";
import {PlaylistModifyButtonImg} from '../../style/styled_component/video_play';
import {deletePlayList} from '../../service/vide_play/delete_button';

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
