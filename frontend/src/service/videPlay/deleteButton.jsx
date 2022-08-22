import axios from 'axios';

/**
 * 플레이리스트에서 해당 유튜브 동영상 삭제
 * @param checkboxes 플레이리스트의 체크박스들
 * @param playlistDataUpdate 플레이리스트의 상태가 변경되었는지에 대한 상태값이 저장된 랜덤 저장된 변수 (useState)
 * @param setPlaylistUpdate 플레이리스트의 상태가 변경되었는지에 대한 상태값을 변경하는 함수 (useState)
 */
export const deletePlayList = (checkboxes, playlistDataUpdate, setPlaylistUpdate) => {
	axios.delete('http://localhost:8080/api/video', {data: checkboxes})
			.then(() => setPlaylistUpdate(playlistDataUpdate + 1));
};