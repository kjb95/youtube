import React, {useEffect, useState} from 'react';
import './App.css';

import {BrowserRouter, Route, Routes} from 'react-router-dom';
import {HidePlaylistBox, ShowPlaylistBox} from './style/styledComponent/videoPlay.jsx';

import Index from './pages/Index.jsx';
import VideoPlay from './pages/VideoPlay.jsx';
import {useTranslation} from 'react-i18next';
import Navigation from './components/navigation/Navigation.jsx';
import Login from './pages/Login.jsx';
import SignUp from './pages/SignUp.jsx';
import NotFound from './pages/NotFound.jsx'

function App() {
	const [language, setLanguage] = useState(null);

	const {i18n} = useTranslation();

	useEffect(() => {
		const language = localStorage.getItem('language');
		if (language === 'ko') {
			localStorage.setItem('language', 'ko');
		}
		else if (language === 'en') {
			localStorage.setItem('language', 'en');
		}
		if (!language || language === 'ko') {
			i18n.changeLanguage('ko');
		}
		else {
			i18n.changeLanguage('en');
		}
	}, [language, i18n]);
	return (<>
		<Navigation language={language} setLanguage={setLanguage} />
		<BrowserRouter>
			<Routes>
				<Route path='/' element={<Index />} />
				<Route path='/playlist' element={<ShowPlaylistBox>
					<HidePlaylistBox>
						<VideoPlay />
					</HidePlaylistBox>
				</ShowPlaylistBox>} />
				<Route path='/login' element={<Login />} />
				<Route path='/sign-up' element={<SignUp />} />
				<Route path='*' element={<NotFound />} />
			</Routes>
		</BrowserRouter>
	</>);
}

export default App;