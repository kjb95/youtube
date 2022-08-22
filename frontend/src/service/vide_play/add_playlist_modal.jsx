import {getYoutubeData} from "../common.js";
import axios from 'axios';

/**
 * 유튜브 동영상 추가 모달창의 id 입력값에 대한 이벤트 함수
 * @param event id 입력값에 대한 이벤트 객체
 * @param setId 유튜브 동영상 추가 모달창의 입력값인 id의 상태를 변경해 주는 함수 (useState)
 */
export const inputChange = (event, setId) => {
	setId(event.target.value);
};

/**
 * 유튜브 동영상 추가 모달창의 추가 버튼에 대한 이벤트 함수
 * @param id 추가할 유튜브 동영상 아이디
 * @param setAddPlaylistModal 유튜브 동영상 추가에 대한 모달창의 상태를 변경해 주는 함수 (useState)
 * @param setId 유튜브 동영상 추가 모달창의 입력값인 id의 상태를 변경해 주는 함수 (useState)
 */
export const addPlaylist = (id, setAddPlaylistModal, setId) => {
	getYoutubeData(id)
			.then(playlist => {
				axios.post('http://localhost:8080/api/video', playlist)
						.then(() => {
							setAddPlaylistModal(false);
							setId("");
						});
			});
};

/**
 * 유튜브 동영상 추가 모달창의 닫기 버튼에 대한 이벤트 함수
 * @param setAddPlaylistModal 유튜브 동영상 추가에 대한 모달창의 상태를 변경해 주는 함수 (useState)
 * @param setId 유튜브 동영상 추가 모달창의 입력값인 id의 상태를 변경해 주는 함수 (useState)
 */
export const closeModal = (setAddPlaylistModal, setId) => {
	setAddPlaylistModal(false);
	setId("");
};
