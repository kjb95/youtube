import React from 'react';
import {IndexVideoImg, PlaylistInformationBox, PlaylistTitleBox, PlaylistYoutuberBox} from '../../style/styledComponent/index';

const IndexVideoElement = ({
	id,
	title,
	channelTitle,
	viewCount,
	publishedAt
}) => {
	const youtbeAddress = `/playlist?page=${id}`;
	const videoImgAddress = `https://img.youtube.com/vi/${id}/mqdefault.jpg`;

	return (<a href={youtbeAddress}>
		<IndexVideoImg src={videoImgAddress} alt='video_img' />
		<PlaylistTitleBox>{title}</PlaylistTitleBox>
		<PlaylistYoutuberBox>{channelTitle}</PlaylistYoutuberBox>
		<PlaylistInformationBox>{viewCount} ⦁ {publishedAt}</PlaylistInformationBox>
	</a>);
};

export default IndexVideoElement;
