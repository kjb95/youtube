import React from "react";
import { PlaylistModifyButtonImg } from '../../style/styled_component/video_play';
import { deletePlayList } from '../../service/vide_play/delete_button';

const DeleteButton = ({checkboxs, playlistDataUpdate, setPlaylistUpdate}) => {
  return (
    <PlaylistModifyButtonImg 
      src="./img/deleteButton.png"
      alt="deleteButton"
      onClick={() => { deletePlayList(checkboxs, playlistDataUpdate, setPlaylistUpdate); }}
    />
  );
};

export default DeleteButton;
