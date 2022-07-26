import React from "react";
import {
  PlaylistInformationBox,
  PlaylistTitleBox,
  PlaylistYoutuberBox,
  IndexVideoImg,
} from "../../style/styled_component/index";

const IndexVideoElement = ({
  id,
  title,
  channelTitle,
  viewCount,
  publishedAt
}) => {
  const youtbeAddress = `/video_play?page=${id}`;
  const videoImgAddress = `https://img.youtube.com/vi/${id}/mqdefault.jpg`

  return (
    <a href={youtbeAddress}>
      <IndexVideoImg src={videoImgAddress} alt="video_img" />
      <PlaylistTitleBox>{title}</PlaylistTitleBox>
      <PlaylistYoutuberBox>{channelTitle}</PlaylistYoutuberBox>
      <PlaylistInformationBox>{viewCount} ⦁ {publishedAt}</PlaylistInformationBox>
    </a>
  );
};

export default IndexVideoElement;
