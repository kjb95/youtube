import React from 'react';
import {PlaylistAnchorBox, PlaylistBox, PlaylistVideoImg} from '../../style/styledComponent/videoPlay.jsx';
import {PlaylistInformationBox, PlaylistTitleBox, PlaylistYoutuberBox} from '../../style/styledComponent/index';

const PlaylistElement = ({
	playlistName,
	id,
	title,
	channelTitle,
	viewCount,
	publishedAt,
	checkboxChange = null
}) => {

	const url = `/playlist?page=${id}`;
	const thumbnailAddress = `https://img.youtube.com/vi/${id}/mqdefault.jpg`;

	return <PlaylistBox className={playlistName}>
		<input type='checkbox' id={id} name={id} onClick={checkboxChange} />
		<PlaylistAnchorBox playlistName={playlistName}> <a href={url}>
			<PlaylistVideoImg src={thumbnailAddress} alt='video_img' />
			<div>
				<PlaylistTitleBox>{title}</PlaylistTitleBox>
				<PlaylistYoutuberBox>{channelTitle}</PlaylistYoutuberBox>
				<PlaylistInformationBox>{viewCount} ⦁ {publishedAt}</PlaylistInformationBox>
			</div>
		</a> </PlaylistAnchorBox> </PlaylistBox>;
};

export default PlaylistElement;
