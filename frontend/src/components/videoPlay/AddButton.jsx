import React from 'react';
import {PlaylistModifyButtonImg} from '../../style/styledComponent/videoPlay.jsx';
import {openAddPlaylistModal} from '../../service/videPlay/addButton.jsx';

const AddButton = ({setAddPlaylistModal}) => {
	return <PlaylistModifyButtonImg src='./img/addButton.png' alt='addButton' onClick={() => openAddPlaylistModal(setAddPlaylistModal)} />;
};

export default AddButton;
