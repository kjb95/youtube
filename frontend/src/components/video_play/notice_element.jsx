import React from "react";
import {NoticeCloseButtonImg, NoticeDoNotSeeTodayInput, NoticeImg,} from "../../style/styled_component/video_play";
import {useTranslation} from "react-i18next";

const NoticeElement = ({
	title,
	text,
	img,
	name,
	noticeClose,
	clickDoNotSeeToday,
}) => {
	const {t} = useTranslation();

	return (<>
		<NoticeCloseButtonImg src='../img/deleteButton.png' alt='close_button' onClick={noticeClose} name={name} />
		<NoticeImg src={img} className='notice_img' alt='notice_img' />
		<h3>{title}</h3>
		<div>{text}</div>
		<NoticeDoNotSeeTodayInput type='checkbox' className='do_not_see_today' name={name} onClick={clickDoNotSeeToday} />
		<span>{t('doNotSeeToday')}</span>
	</>);
};

export default NoticeElement;
