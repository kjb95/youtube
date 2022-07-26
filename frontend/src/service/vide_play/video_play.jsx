import { getCookie, setCookie } from "../common.js";

export const viewMore = (viewMoreRef, descriptionRef, brieflyRef) => {
  viewMoreRef.current.style.display = 'none';
  descriptionRef.current.style.display = 'block';
  brieflyRef.current.style.display = 'block';
}

export const briefly = (viewMoreRef, descriptionRef, brieflyRef) => {
  viewMoreRef.current.style.display = 'block';
  descriptionRef.current.style.display = '-webkit-box';
  brieflyRef.current.style.display = 'none';
}

export const isNoticeAllClose = (notice, isNoticeClose) => {
  let isAllClose = true;

  notice.map((data, index) => {
    if (isNoticeClose && isNoticeClose[index])
      return '';
    if (getCookie(index) !== '')
      return '';
    isAllClose = false;
    return ''
  })

  return isAllClose;
}

export const checkboxChange = (event, checkboxs, setCheckboxs) => {
  const { name, checked } = event.target;
  setCheckboxs({
    ...checkboxs,
    [name]: checked,
  });
};

export const clickDoNotSeeToday = (event, noticeCookie, setNoticeCookie) => {
  const { name } = event.target;

  if (event.target.checked === true)
    setNoticeCookie({
      ...noticeCookie,
      [name]: true,
    });
  else
    setNoticeCookie({
      ...noticeCookie,
      [name]: false,
    });
};

export const noticeClose = (event, isNoticeClose, setIsNoticeClose, noticeCookie) => {
  const { name } = event.target;
  setIsNoticeClose({
    ...isNoticeClose,
    [name]: true,
  });

  if (noticeCookie && noticeCookie[name] === true)
    setCookie(name, "true", 86400000); // 하루동안 열지 않기
};

export const goNextPlaylist = (nextPlaylist) => {
  if (nextPlaylist === undefined)
    return null;
  document.location.href = `/video_play?page=${nextPlaylist.id}`;
}