import React, {useEffect, useRef, useState} from 'react';
import {useLocation} from 'react-router';
import ReactPlayer from 'react-player';
import qs from 'qs';
import axios from 'axios';
import {useTranslation} from 'react-i18next';

import {fetchNotice, setPlaylist} from '../service/common.js';
import {briefly, checkboxChange, clickDoNotSeeToday, goNextPlaylist, isNoticeAllClose, noticeClose, viewMore} from '../service/videPlay/videoPlay.jsx';

import Playlist from '../components/videoPlay/Playlist.jsx';
import RandomButton from '../components/videoPlay/RandomButton.jsx';
import AddButton from '../components/videoPlay/AddButton.jsx';
import AddPlaylistModal from '../components/videoPlay/AddPlaylistModal.jsx';
import DeleteButton from '../components/videoPlay/DeleteButton.jsx';
import Notice from '../components/videoPlay/Notice.jsx';

import '../style/css/common.css';
import {NoticeBox, PlayingVideoDescriptionBox, PlayingVideoDescriptionBriefly, PlayingVideoDiscriptionSection1, PlayingVideoDiscriptionSection2, PlayingVideoDiscriptionSection3, PlayingVideoDiscriptionViewMore, PlayingVideoInformationBox, PlayingVideoMain, PlayingVideoSubscriberBox, PlayingVideoTitleBox, PlayingVideoYoutuberBox, PlayingYoutubePlayerSection, PlaylistModifyButtonsSection, PlaylistsSection} from '../style/styledComponent/videoPlay.jsx';

const VideoPlay = () => {
	// window.localStorage.clear();
	const location = useLocation();
	const query = qs.parse(location.search, {ignoreQueryPrefix: true});
	const youtubeURL = 'https://www.youtube.com/watch?v=' + query.page;

	const [sequentialPlaylist, setSequentialPlaylist] = useState(undefined);
	const [randomPlaylist, setRandomPlaylist] = useState(undefined);
	const [addPlaylistModal, setAddPlaylistModal] = useState(false);
	const [notice, setNotice] = useState(undefined);
	const [checkboxes, setCheckboxes] = useState();
	const [noticeCookie, setNoticeCookie] = useState();
	const [isNoticeClose, setIsNoticeClose] = useState();
	const [currentPlaylist, setCurrentPlaylist] = useState(undefined);
	const [nextPlaylist, setNextPlaylist] = useState(undefined);
	const [random, setRandom] = useState(false);
	const [playlistDataUpdate, setPlaylistUpdate] = useState(0);

	const viewMoreRef = useRef();
	const brieflyRef = useRef();
	const descriptionRef = useRef();

	const {
		t,
		i18n
	} = useTranslation();

	useEffect(() => {
		// window.localStorage.clear();
		const language = localStorage.getItem('language');
		const langParameter = language ?? 'ko';

		axios.get(`http://localhost:8080/api/video?language=${langParameter}`)
				.then(res => setSequentialPlaylist(res.data));

		const localStorageRandomPlaylist = JSON.parse(localStorage.getItem('randomPlaylist'));
		if (localStorageRandomPlaylist) {
			setRandomPlaylist(localStorageRandomPlaylist);
		}
		else {
			axios.get(`http://localhost:8080/api/video/random-value?language=${langParameter}`)
					.then(res => {
						localStorage.setItem('randomPlaylist', JSON.stringify(res.data));
						setRandomPlaylist(res.data)
					});
		}

		fetchNotice()
				.then((data) => setNotice(data));

		const isRandom = localStorage.getItem('isRandom');
		if (isRandom === 'true') {
			setRandom(true);
		}
		else {
			setRandom(false);
		}
	}, [addPlaylistModal, playlistDataUpdate, i18n, t]);

	useEffect(() => {
		if (!sequentialPlaylist || !randomPlaylist) {
			return;
		}
		if (random) {
			setPlaylist(randomPlaylist, query.page, setCurrentPlaylist, setNextPlaylist);
		}
		else {
			setPlaylist(sequentialPlaylist, query.page, setCurrentPlaylist, setNextPlaylist);
		}

	}, [sequentialPlaylist, randomPlaylist, query.page, random]);

	if (!sequentialPlaylist || !randomPlaylist || !notice || !currentPlaylist) {
		return '';
	}

	return (<>
		<aside>
			<PlaylistsSection> <Playlist checkboxChange={(event) => {
				checkboxChange(event, checkboxes, setCheckboxes);
			}} sequentialPlaylist={sequentialPlaylist} randomPlaylist={randomPlaylist} />
			</PlaylistsSection> <PlaylistModifyButtonsSection>
			<input type='hidden' id='isRandom' value='false' />
			<RandomButton playlistDataUpdate={playlistDataUpdate} setPlaylistUpdate={setPlaylistUpdate} />
			<AddButton setAddPlaylistModal={setAddPlaylistModal} />
			<DeleteButton checkboxes={checkboxes} playlistDataUpdate={playlistDataUpdate} setPlaylistUpdate={setPlaylistUpdate} />
		</PlaylistModifyButtonsSection>
		</aside>

		<PlayingVideoMain id='video'>
			<AddPlaylistModal addPlaylistModal={addPlaylistModal} setAddPlaylistModal={setAddPlaylistModal} /> {!isNoticeAllClose(notice, isNoticeClose) && ( // 공지사항이 모두 닫히지 않았다면
				<NoticeBox id='notice'>
					<Notice notice={notice} isNoticeClose={isNoticeClose} noticeClose={(event) => {
						noticeClose(event, isNoticeClose, setIsNoticeClose, noticeCookie);
					}} clickDoNotSeeToday={(event) => {
						clickDoNotSeeToday(event, noticeCookie, setNoticeCookie);
					}} /> </NoticeBox>)} <PlayingYoutubePlayerSection>
			<ReactPlayer url={youtubeURL} width='100%' height='100%' playing controls onEnded={() => {
				goNextPlaylist(nextPlaylist);
			}} /> </PlayingYoutubePlayerSection>
			<PlayingVideoDiscriptionSection1>
			<PlayingVideoTitleBox>{currentPlaylist.title}</PlayingVideoTitleBox>
			<PlayingVideoInformationBox>
				{currentPlaylist.viewCount} ⦁ {currentPlaylist.publishedAt}
			</PlayingVideoInformationBox> </PlayingVideoDiscriptionSection1>

			<PlayingVideoDiscriptionSection2> <PlayingVideoYoutuberBox>
				{currentPlaylist.channelTitle}
			</PlayingVideoYoutuberBox> <PlayingVideoSubscriberBox>
				{currentPlaylist.subscriberCount}
			</PlayingVideoSubscriberBox> </PlayingVideoDiscriptionSection2>

			<PlayingVideoDiscriptionSection3>
				<PlayingVideoDescriptionBox ref={descriptionRef}>
					{currentPlaylist.description}
				</PlayingVideoDescriptionBox>
				<PlayingVideoDiscriptionViewMore onClick={() => {
					viewMore(viewMoreRef, descriptionRef, brieflyRef);
				}} ref={viewMoreRef}>
					{t('viewMore')}
				</PlayingVideoDiscriptionViewMore>
				<PlayingVideoDescriptionBriefly onClick={() => {
					briefly(viewMoreRef, descriptionRef, brieflyRef);
				}} ref={brieflyRef}>
					{t('briefly')}
				</PlayingVideoDescriptionBriefly> </PlayingVideoDiscriptionSection3>
		</PlayingVideoMain>
	</>);
};

export default VideoPlay;
