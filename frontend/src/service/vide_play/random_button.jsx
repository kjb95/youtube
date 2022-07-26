export const randomPlay = (event, playlistDataUpdate, setPlaylistUpdate) => {
  const isRandom = window.localStorage.getItem('isRandom');

  if (isRandom === 'true') {
    event.target.style.opacity = 0.4;
    window.localStorage.setItem('isRandom', false);
  } else {
    event.target.style.opacity = 1;
    window.localStorage.setItem('isRandom', true);
  }
  setPlaylistUpdate(playlistDataUpdate+1);
}