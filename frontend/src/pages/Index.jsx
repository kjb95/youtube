import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {useTranslation} from 'react-i18next';

import '../style/css/common.css';

import {IndexVideoMain} from '../style/styledComponent/index';
import IndexVideoElement from '../components/index/IndexVideoElement.jsx';

const Index = () => {
	const [playlist, setPlaylist] = useState(undefined);
	const {t} = useTranslation();

	useEffect(() => {
		// window.localStorage.clear();
		const lang = localStorage.getItem('lang');
		const langParameter = lang ?? 'ko';

		axios.get(`http://localhost:8080/api/video?lang=${langParameter}`)
				.then(res => setPlaylist(res.data));
	}, [t]);

	if (!playlist) {
		return '';
	}

	return (<IndexVideoMain>
		{playlist.map((playlist) => {
			if (playlist.exist !== true) {
				return '';
			}
			return <li key={playlist.id}>
				<IndexVideoElement id={playlist.id} title={playlist.title} channelTitle={playlist.channelTitle} viewCount={playlist.viewCount} publishedAt={playlist.publishedAt} />
			</li>;
		})}
	</IndexVideoMain>);
};

export default Index;
