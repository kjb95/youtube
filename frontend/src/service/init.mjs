import fs from 'fs';
import path from 'path';
import {fileURLToPath} from 'url';
import {getYoutubeData} from './common.js';
import dotenv from 'dotenv';
import axios from 'axios';

dotenv.config({path: '../../.env'});

/**
 * frontend 루트 폴더에서 npm run init 명령어를 입력하면 init() 실행됨
 * public/json/playlist.json 에 저장된 동영상 아이디 리스트로 유튜브 API 호출하여 얻은 동영상 리스트를 디비에 저장
 */
const addInitialData = async () => {
	const __filename = fileURLToPath(import.meta.url);  // 경로가 포함된 햔재 파일 이름
	const __dirname = path.dirname(__filename); // 현재 파일의 경로
	const filePath = path.join(__dirname, '../../public/json/playlist.json');  // 찾으려는 파일의 경로
	const jsonFile = fs.readFileSync(filePath);
	const parsedJsonData = JSON.parse(jsonFile);
	const playlist = {
		createVideoRequestDtoList: []
	};

	for (let i = 0; i < parsedJsonData.playlist.length; i++) {
		playlist.createVideoRequestDtoList.push(await getYoutubeData(parsedJsonData.playlist[i].id));
	}

	axios.post('http://localhost:8080/api/video/initial-value', playlist)
			.then(res => {
				console.log('success!');
			})
			.catch((err) => {
				console.log('error!');
			});
};

addInitialData();