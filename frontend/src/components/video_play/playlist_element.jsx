import React from "react";
import {
  PlaylistAnchorBox,
  PlaylistBox,
  PlaylistVideoImg,
} from "../../style/styled_component/video_play";
import {
  PlaylistInformationBox,
  PlaylistTitleBox,
  PlaylistYoutuberBox,
} from "../../style/styled_component/index";

const PlaylistElement = ({
  playlistName,
  id,
  title,
  channelTitle,
  viewCount,
  publishedAt,
  checkboxChange = null,
}) => {

  const youtbeAddress = `/video_play?page=${id}`;
  const videoImgAddress = `https://img.youtube.com/vi/${id}/mqdefault.jpg`;

  return <PlaylistBox className={playlistName}>
    <input
      type="checkbox"
      id={id}
      name={id}
      onClick={checkboxChange}
    />
    <PlaylistAnchorBox playlistName={playlistName}>
      <a href={youtbeAddress}>
        <PlaylistVideoImg src={videoImgAddress} alt="video_img" />
        <div>
          <PlaylistTitleBox>{title}</PlaylistTitleBox>
          <PlaylistYoutuberBox>{channelTitle}</PlaylistYoutuberBox>
          <PlaylistInformationBox>{viewCount} ⦁ {publishedAt}</PlaylistInformationBox>
        </div>
      </a>
    </PlaylistAnchorBox>
  </PlaylistBox>
};

export default PlaylistElement;
