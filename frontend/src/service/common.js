import axios from 'axios';
import dotenv from 'dotenv';

dotenv.config();

/**
 * 유튜브 동영상 플레이리스트 중에서 현재 동영상과 다음 동영상을 설정
 * @param playlist 동영상 리스트 (순차재생 리스트 또는 랜덤재생 리스트)
 * @param page URL query 스트링
 * @param setCurrentPlaylist 현재 동영상의 상태를 갱신하는 함수 (useState)
 * @param setNextPlaylist 다음 동영상의 상태를 갱신하는 함수 (useState)
 */
export const setPlaylist = (playlist, page, setCurrentPlaylist, setNextPlaylist) => {
	let findCurrentPlaylist = false;

	for (let i = 0; i < playlist.length; i++) {
		if (findCurrentPlaylist === true && playlist[i].exist) {
			setNextPlaylist(playlist[i]);
			break;
		}
		if (playlist[i].id === page) {
			setCurrentPlaylist(playlist[i]);
			findCurrentPlaylist = true;
		}
	}
}

/**
 * notice.json 읽기
 * @return 공지사항 정보가 담긴 데이터
 */
export const fetchNotice = async () => {
	return await fetch('/json/notice.json')
			.then((response) => response.json())
			.then(data => data.notices);
}

/**
 * 쿠키 생성 또는 수정
 * @param name 생성 또는 수정될 쿠키의 이름
 * @param value 쿠키의 이름에 매핑되는 쿠키의 값
 * @param expire 쿠키의 유효시간
 */
export const setCookie = (name, value, expire) => {
	const currentDay = new Date();
	currentDay.setTime(currentDay.getTime() + expire);
	document.cookie = name + "=" + value + "; expires=" + currentDay.toUTCString();
}

/**
 * 쿠키 name으로 value 조회
 * @param name 쿠키의 이름
 * @return 쿠키의 이름에 매핑되는 쿠기 값
 */
export const getCookie = (name) => {
	if (document.cookie === "") return "";

	const cookies = document.cookie.split(";");

	for (let i in cookies) {
		const key = (Number)(cookies[i].split("=")[0].trim());
		const value = cookies[i].split("=")[1].trim();
		if (key === name) return value;
	}
	return "";
}

/**
 * 유튜브 API 호출
 * @return 유튜브 API 호출로 얻은 데이터
 */
async function callYoutubeAPI(type, part, id) {
	return await axios.get(`https://www.googleapis.com/youtube/v3/${type}?part=${part}&id=${id}&key=${process.env.REACT_APP_YOUTUBE_API}`)
			.then(res => res.data.items[0]);
}

/**
 * 유튜브 동영상 아이디로 유튜브 API를 호출하여 데이터 조회
 * @param id 조회할 유튜브 동영상 아이디
 * @return 유튜브 동영상 아이디로 얻은 데이터
 */
export async function getYoutubeData(id) {
	let data1 = await callYoutubeAPI('videos', 'snippet', id);
	const data2 = await callYoutubeAPI('videos', 'statistics', id);
	const data3 = await callYoutubeAPI('channels', 'statistics', data1.snippet.channelId);

	data1 = data1.snippet;
	return {
		id: id,
		channelId: data1.channelId,
		channelTitle: data1.channelTitle,
		description: data1.description,
		title: data1.title,
		publishedAt: data1.publishedAt,
		viewCount: data2.statistics.viewCount,
		subscriberCount: data3.statistics.subscriberCount
	}
}

/**
 * public/json/playlist.json 에 저장된 동영상 아이디 리스트 조회
 * @return 조회할 동영상 아이디 리스트
 */
async function fetchPlaylist() {
	return await fetch('json/playlist.json')
			.then((res) => res.json())
			.then(data => data.playlist);
}

/**
 * public/json/playlist.json 에 저장된 동영상 아이디 리스트로 유튜브 API 호출하여 동영상 리스트 조회
 * @return 유튜브 API를 호출하여 얻은 동영상 리스트
 */
export async function getYoutubeDataList() {
	let playlist = [];

	await fetchPlaylist()
			.then(data => {
				data.forEach(data => {
					playlist.push(getYoutubeData(data.id));
				})
			});

	return await Promise.all(playlist);
}