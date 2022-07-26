import React from "react";
import { PlaylistModifyButtonImg } from "../../style/styled_component/video_play";
import { openAddPlaylistModal } from '../../service/vide_play/add_button'

const AddButton = ({ setAddPlaylistModal }) => {
  return <PlaylistModifyButtonImg 
    src="./img/addButton.png" 
    alt="addButton" 
    onClick={() => openAddPlaylistModal(setAddPlaylistModal)}
  />
};

export default AddButton;
