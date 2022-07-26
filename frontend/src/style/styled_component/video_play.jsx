import styled from "styled-components";

import { OverflowBox } from './common';

export const PlayingVideoInformationBox = styled.div`
  color: gray;
  font-size: large;
`;
export const PlayingVideoSubscriberBox = styled.div`
  color: gray;
  font-size: small;
`;
export const PlayingVideoYoutuberBox = styled(OverflowBox)`
  color: white;
  font-size: large;
`;
export const PlayingVideoTitleBox = styled.div`
  color: white;
  font-size: x-large;
`;
export const PlayingVideoDescriptionBox = styled(OverflowBox)`
  color: white;
`;
export const PlayingYoutubePlayerSection = styled.section`
  margin-top: 5%;
  width: 100%;
  height: 70vh;
`;
export const PlayingVideoDiscriptionSection1 = styled.section`
  margin-left: 1%;
`;
export const PlayingVideoDiscriptionSection2 = styled.section`
  padding-left: 8%;
`;
export const PlayingVideoDiscriptionSection3 = styled.section`
  padding-left: 8%;
`;
export const PlayingVideoMain = styled.main`
  & > *{
    margin-top: 5%;
  }
`
export const PlayingVideoDiscriptionViewMore = styled.div`
  color: gray;
  font-size: small;
  cursor: pointer;
`
export const PlayingVideoDescriptionBriefly = styled.div`
  color: gray;
  font-size: small;
  cursor: pointer;
  display: none;
`

export const AddPlaylistFormBox = styled.div`
  color: gray;
  margin: 1%;
`;
export const AddPlaylistButtonsSection = styled.section`
  margin-top: 5%;

  & > * {
    margin: 1%;
  }
`;

export const NoticeBox = styled.div`
  text-align: center;
  display: flex;
  position: fixed;
  left: 2%;
  top: 10%;
  height: 64%;

  & > * {
    background-color: white;
    padding: 1%;
    text-align: center;
    overflow: scroll;
    width: 100%;
    border: thick dotted black;
  }
`;
export const NoticeCloseButtonImg = styled.img`
  margin: 1%;
  cursor: pointer;
  display: block;
  width: 5%;
`;
export const NoticeDoNotSeeTodayInput = styled.input`
  margin-top: 5%;
`;
export const NoticeImg = styled.img`
  margin-bottom: 1%;
  width: 50%;
`;

export const PlaylistsSection = styled.section`
  padding: 1%;
  overflow: scroll;
  height: 70vh;
`;
export const PlaylistVideoImg = styled.img`
  padding: 1%;
  width: 40%;
`;
export const PlaylistBox = styled.div`
  padding: 1%;
  display: flex;
`;

export const PlaylistModifyButtonsSection = styled.section`
  text-align: center;
  display: flex;
  display: inline-block;
`;
export const PlaylistModifyButtonImg = styled.img`
  background-color: gray;
  margin: 5%;
  cursor: pointer;
  width: 15%;
`;
export const PlaylistRandomButtonImg = styled(PlaylistModifyButtonImg)`
  opacity: ${props => props.isRandom === 'true' ? 1 : 0.4};
`;
export const PlaylistAnchorBox = styled.div`
  & a {
    padding: 1%;
    display: flex;
    background-color: ${(props) =>
      props.playlistName === "current_playlist" ? "#dcdcdc" : "black"};
  }
`;

export const ShowPlaylistBox = styled.div`
  @media screen and (min-width: 961px) {
    main {
      float: left;
      width: 66%;
    }
    aside {
      float: right;
      width: 30%;
    }
    #notice {
      width: 63%;
    }
  }
`;
export const HidePlaylistBox = styled.div`
  @media screen and (max-width: 960px) {
    main {
      width: 100%;
    }
    aside {
      display: none;
    }
    #notice {
      width: 95%;
    }
  }
`;
