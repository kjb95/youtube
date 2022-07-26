import axios from 'axios';
import dotenv from 'dotenv';
dotenv.config();

export const setPlaylist = (playlist, page, setCurrentPlaylist, setNextPlaylist) => {
  let findCurrentPlaylist = false;

  for(let i=0; i<playlist.length; i++) {
    if (findCurrentPlaylist === true && playlist[i].exist){
      setNextPlaylist(playlist[i]);
      break ;
    }
    if (playlist[i].id === page) {
      setCurrentPlaylist(playlist[i]);
      findCurrentPlaylist = true;
    }
  }
}

export const fetchNotice = async () => {
  return await fetch('/json/notice.json')
    .then((response) => response.json())
    .then(data => data.notices);
}

export const setCookie = (name, value, expire) => {
  const currentDay = new Date();
  currentDay.setTime(currentDay.getTime() + expire);
  document.cookie =
    name + "=" + value + "; expires=" + currentDay.toUTCString();
}

export const getCookie = (name) => {
  if (document.cookie === "") return "";

  const cookies = document.cookie.split(";");

  for (let i in cookies) {
    const key = (Number)(cookies[i].split("=")[0].trim());
    const value = cookies[i].split("=")[1].trim();
    if (key === name) return value;
  }
  return "";
}

async function callYoutubeAPI(type, part, id) {
  return await axios.get(`https://www.googleapis.com/youtube/v3/${type}?part=${part}&id=${id}&key=${process.env.REACT_APP_YOUTUBE_API}`)
    .then(res => res.data.items[0]);
}

export async function getYoutubeData(id) {
  let data1 = await callYoutubeAPI('videos', 'snippet', id);
  const data2 = await callYoutubeAPI('videos', 'statistics', id);
  const data3 = await callYoutubeAPI('channels', 'statistics', data1.snippet.channelId);

  data1 = data1.snippet;
  return {
    id: id,
    channelId: data1.channelId,
    channelTitle: data1.channelTitle,
    description: data1.description,
    title: data1.title,
    publishedAt: data1.publishedAt,
    viewCount: data2.statistics.viewCount,
    subscriberCount: data3.statistics.subscriberCount,
    exist: true
  }
}

async function fetchPlaylist() {
  return await fetch('json/playlist.json')
    .then((res) => res.json())
    .then(data => data.playlist);
}

export async function getYoutubeDataList() {
  let playlist = [];

  await fetchPlaylist().then(data => {
    data.forEach(data => {
      playlist.push(getYoutubeData(data.id));
    })
  });
  
  return await Promise.all(playlist);

  // let pl = [];
  //   pl[0] = {
  //     id: 'hn4XiirKdNE',
  //     channelTitle:'channelTitle1',
  //     description:'description1',
  //     publishedAt:'publishedAt1',
  //     title:'title1',
  //     viewCount:'viewCount1',
  //     subscriberCount:'subscriberCount1',
  //     isExist: true
  //   }
  //   pl[1] = {
  //     id:'V60QQDA57SA',
  //     channelTitle:'channelTitle2',
  //     description:'description2',
  //     publishedAt:'publishedAt2',
  //     title:'title2',
  //     viewCount:'viewCount2',
  //     subscriberCount:'subscriberCount2',
  //     isExist: true
  //   }
  //   pl[2] = {
  //     id:'X3S4ju8BZ-w',
  //     channelTitle:'channelTitle3',
  //     description:'description3',
  //     publishedAt:'publishedAt3',
  //     title:'title3',
  //     viewCount:'viewCount3',
  //     subscriberCount:'subscriberCount3',
  //     isExist: true
  //   }  
  // return pl;
}