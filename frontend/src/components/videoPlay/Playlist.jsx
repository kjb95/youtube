import React from 'react';

import PlaylistElement from './PlaylistElement.jsx';

const Playlist = ({
	checkboxChange,
	sequentialPlaylist,
	randomPlaylist
}) => {
	const isRandom = window.localStorage.getItem('isRandom');
	if (!isRandom) {
		window.localStorage.setItem('isRandom', false);
	}
	if (isRandom === 'true') {
		return loadPlaylist(randomPlaylist, checkboxChange);
	}

	return loadPlaylist(sequentialPlaylist, checkboxChange);
};

function loadPlaylist(data, checkboxChange) {
	const url = new URL(window.location.href);

	return data.map((playlistElement) => {
		if (!playlistElement.exist) {
			return '';
		}

		let playlistName = 'playlist';
		if (url.searchParams.get('page') === playlistElement.id) {
			playlistName = 'current_playlist';
		}

		return <li key={playlistElement.id}>
			<PlaylistElement playlistName={playlistName} id={playlistElement.id} title={playlistElement.title} channelTitle={playlistElement.channelTitle} viewCount={playlistElement.viewCount} publishedAt={playlistElement.publishedAt} checkboxChange={checkboxChange} />
		</li>;
	});
}

export default Playlist;
