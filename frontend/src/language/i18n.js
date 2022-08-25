import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';

import translationEn from './translation.en.json';
import translationKo from './translation.ko.json';

const resources = {
	en: {
		translation: translationEn
	},
	ko: {
		translation: translationKo
	}
};

i18n.use(initReactI18next) // passes i18n down to react-i18next
		.init({resources});

export default i18n;