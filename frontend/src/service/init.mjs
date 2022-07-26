import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';
import {getYoutubeData} from "./common.js";
import dotenv from 'dotenv';
import axios from 'axios';

dotenv.config({path: "../../.env"});

const init = async () => {
  const __filename = fileURLToPath(import.meta.url);  // 경로가 포함된 햔재 파일 이름
  const __dirname = path.dirname(__filename); // 현재 파일의 경로
  const filePath = path.join(__dirname, "../../public/json/playlist.json");  // 찾으려는 파일의 경로
  const jsonFile = fs.readFileSync(filePath);
  const jsonData = JSON.parse(jsonFile);
  let playlist = [];

  for(let i=0; i<jsonData.playlist.length; i++)
      playlist.push(await getYoutubeData(jsonData.playlist[i].id));
  axios.post('http://localhost:8080/api/playlist/initial-value',playlist)
    .then(res => {
      console.log('res : ', res);
    }).catch(function(err) {
      console.log('err : ', err);
  })
}

init();