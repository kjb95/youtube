import React from 'react';
import SelectLanguage from './SelectLanguage.jsx';
import {NavigationBox, NavigationLeftBox, NavigationRightBox} from '../../style/styledComponent/navigation';
import Home from './Home.jsx';
import LoginButton from './LoginButton.jsx';

function Navigation({
	language,
	setLanguage
}) {
	return (<NavigationBox> <NavigationLeftBox> <Home /> </NavigationLeftBox>
		<NavigationRightBox>
			<SelectLanguage language={language} setLanguage={setLanguage} />
			<LoginButton /> </NavigationRightBox> </NavigationBox>);
}

export default Navigation;