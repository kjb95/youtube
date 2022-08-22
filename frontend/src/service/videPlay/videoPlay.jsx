import {getCookie, setCookie} from '../common.js';

/**
 * 유튜브 동영상 설명의 더보기 버튼에 대한 이벤트 함수
 * @param viewMoreRef 유튜브 동영상 설명의 더보기 버튼의 참조값 (useRef)
 * @param descriptionRef 유튜브 동영상 설명에 대한 참조값 (useRef)
 * @param brieflyRef 유튜브 동영상 설명의 간략히 버튼에 대한 참조값 (useRef)
 */
export const viewMore = (viewMoreRef, descriptionRef, brieflyRef) => {
	viewMoreRef.current.style.display = 'none';
	descriptionRef.current.style.display = 'block';
	brieflyRef.current.style.display = 'block';
};

/**
 * 유튜브 동영상 설명의 간략히 버튼에 대한 이벤트 함수
 * @param viewMoreRef 유튜브 동영상 설명의 더보기 버튼의 참조값 (useRef)
 * @param descriptionRef 유튜브 동영상 설명에 대한 참조값 (useRef)
 * @param brieflyRef 유튜브 동영상 설명의 간략히 버튼에 대한 참조값 (useRef)
 */
export const briefly = (viewMoreRef, descriptionRef, brieflyRef) => {
	viewMoreRef.current.style.display = 'block';
	descriptionRef.current.style.display = '-webkit-box';
	brieflyRef.current.style.display = 'none';
};

/**
 * 모든 공지사항이 닫혔는지 확인
 * @param notice 모든 공지사항에 대한 데이터
 * @param isNoticeClose 공지사항이 닫혔는지 에 대한 상태값이 저장된 변수 (useState)
 * @return 모든 공지사항이 닫혀있으면 true, 아니면 false
 */
export const isNoticeAllClose = (notice, isNoticeClose) => {
	let isAllClose = true;

	notice.map((data, index) => {
		if (isNoticeClose && isNoticeClose[index]) {
			return '';
		}
		if (getCookie(index) !== '') {
			return '';
		}
		isAllClose = false;
		return '';
	});

	return isAllClose;
};

/**
 * 체크박스의 상태 변경에 대한 이벤트 함수
 * @param event 체크 박스에 대한 event 객체
 * @param checkboxes 체크 박스에 대한 상태값이 저장된 변수 (useState)
 * @param setCheckboxes 체크 박스에 대한 상태값을 갱신하는 함수 (useState)
 */
export const checkboxChange = (event, checkboxes, setCheckboxes) => {
	const {
		name,
		checked
	} = event.target;
	setCheckboxes({
		...checkboxes,
		[name]: checked
	});
};

/**
 * 공지사항 하루동안 열지 않기 버튼에 대한 이벤트 함수
 * @param event 공지사항 하루동안 열지 않기 버튼에 대한 event 객체
 * @param noticeCookie 공지사항 하루동안 열지 않기에 대한 쿠기의 상태값이 저장된 변수 (useState)
 * @param setNoticeCookie 공지사항 하루동안 열지 않기에 대한 쿠기의 상태값을 갱신하는 함수 (useState)
 */
export const clickDoNotSeeToday = (event, noticeCookie, setNoticeCookie) => {
	const {name} = event.target;

	if (event.target.checked === true) {
		setNoticeCookie({
			...noticeCookie,
			[name]: true
		});
	}
	else {
		setNoticeCookie({
			...noticeCookie,
			[name]: false
		});
	}
};

/**
 * 공지사항 페이지 닫기에 대한 이벤트 함수
 * @param event 공지사항 페이지 닫기 버튼에 대한 event 객체
 * @param isNoticeClose 공지사항 페이지 닫힘 여부의 상태값이 저장된 변수 (useState)
 * @param setIsNoticeClose 공지사항 페이지 닫힘 여부의 상태값을 갱신하는 함수 (useState)
 * @param noticeCookie 공지사항 하루동안 열지 않기에 대한 쿠기의 상태값이 저장된 변수 (useState)
 */
export const noticeClose = (event, isNoticeClose, setIsNoticeClose, noticeCookie) => {
	const {name} = event.target;
	setIsNoticeClose({
		...isNoticeClose,
		[name]: true
	});

	if (noticeCookie && noticeCookie[name] === true) {
		setCookie(name, 'true', 86400000);
	} // 하루동안 열지 않기
};

/**
 * 다음 동영상 페이지로 이동
 */
export const goNextPlaylist = (nextPlaylist) => {
	if (!nextPlaylist) {
		return null;
	}
	document.location.href = `/playlist?page=${nextPlaylist.id}`;
};