import React from 'react';
import {IndexVideoImg, PlaylistInformationBox, PlaylistTitleBox, PlaylistYoutuberBox} from '../../style/styledComponent/index';

const IndexVideoElement = ({
	id,
	title,
	channelTitle,
	viewCount,
	publishedAt
}) => {
	const url = `/playlist?page=${id}`;
	const thumbnailAddress = `https://img.youtube.com/vi/${id}/mqdefault.jpg`;

	return (
			<a href={url}> <IndexVideoImg src={thumbnailAddress} alt='video_img' />
				<PlaylistTitleBox>{title}</PlaylistTitleBox>
				<PlaylistYoutuberBox>{channelTitle}</PlaylistYoutuberBox>
				<PlaylistInformationBox>{viewCount} ⦁ {publishedAt}</PlaylistInformationBox>
			</a>);
};

export default IndexVideoElement;
