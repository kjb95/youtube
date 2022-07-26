import React, { useState } from "react";
import Modal from "react-modal";
import {
  AddPlaylistButtonsSection,
  AddPlaylistFormBox,
} from "../../style/styled_component/video_play";
import {inputChange, addPlaylist, closeModal} from '../../service/vide_play/add_playlist_modal';

const AddPlaylistModal = ({
  addPlaylistModal,
  setAddPlaylistModal,
}) => {
  const [id, setId] = useState('');

  return (
    <Modal
      isOpen={addPlaylistModal}
      ariaHideApp={false}
      style={{ content: { width: "25%", height: "10%" } }}>
      <AddPlaylistFormBox>
        {"유튜브 동영상 아이디 : "}
        <input 
          type="text" 
          name="id" 
          value={id}
          onChange={event => {
            inputChange(event, setId);
          }}/>
      </AddPlaylistFormBox>
      <AddPlaylistButtonsSection>
        <input
          type="submit"
          onClick={() => {
            addPlaylist(
              id,
              setAddPlaylistModal,
              setId
            );
          }}/>
        <button onClick={() => { closeModal(setAddPlaylistModal, setId); }}>닫기</button>
      </AddPlaylistButtonsSection>
    </Modal>
  );
};

export default AddPlaylistModal;
