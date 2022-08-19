import React, {useState} from "react";
import Modal from "react-modal";
import {AddPlaylistButtonsSection, AddPlaylistFormBox,} from "../../style/styled_component/video_play";
import {addPlaylist, closeModal, inputChange} from '../../service/vide_play/add_playlist_modal';
import {useTranslation} from "react-i18next";

const AddPlaylistModal = ({
	addPlaylistModal,
	setAddPlaylistModal,
}) => {
	const [id, setId] = useState('');
	const {t} = useTranslation();

	return (<Modal isOpen={addPlaylistModal} ariaHideApp={false} style={{
		content: {
			width: "25%",
			height: "10%"
		}
	}}> <AddPlaylistFormBox>
		{t('youtubeVideoID') + " : "}
		<input type='text' name='id' value={id} onChange={event => {
			inputChange(event, setId);
		}} /> </AddPlaylistFormBox> <AddPlaylistButtonsSection>
		<input type='submit' value={t('submit')} onClick={() => {
			addPlaylist(id, setAddPlaylistModal, setId);
		}} />
		<button onClick={() => { closeModal(setAddPlaylistModal, setId); }}>{t('close')}</button>
	</AddPlaylistButtonsSection> </Modal>);
};

export default AddPlaylistModal;
