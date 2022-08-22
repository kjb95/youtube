/**
 * 유튜브 동영상 랜덤 버튼에 대한 이벤트 함수
 * @param event 유튜브 동영상 랜덤 버튼에 대한 이벤트 객체
 * @param playlistDataUpdate 플레이리스트의 상태가 변경되었는지에 대한 상태값이 저장된 랜덤 저장된 변수 (useState)
 * @param setPlaylistUpdate 플레이리스트의 상태가 변경되었는지에 대한 상태값을 변경하는 함수 (useState)
 */
export const randomPlay = (event, playlistDataUpdate, setPlaylistUpdate) => {
	const isRandom = window.localStorage.getItem('isRandom');

	if (isRandom === 'true') {
		event.target.style.opacity = 0.4;
		window.localStorage.setItem('isRandom', false);
	}
	else {
		event.target.style.opacity = 1;
		window.localStorage.setItem('isRandom', true);
	}
	setPlaylistUpdate(playlistDataUpdate + 1);
}