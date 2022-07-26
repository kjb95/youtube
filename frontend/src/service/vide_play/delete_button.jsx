import axios from 'axios';

export const deletePlayList = (checkboxs, playlistDataUpdate, setPlaylistUpdate) => {
  axios.delete('http://localhost:8080/api/playlist', {data: checkboxs})
    .then(() => setPlaylistUpdate(playlistDataUpdate+1));
};