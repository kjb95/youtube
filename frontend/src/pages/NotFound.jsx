import {useTranslation} from 'react-i18next'
import {NotFoundH1} from '../style/styledComponent/notFound.jsx'

const NotFound = () => {
	const {t} = useTranslation();

	return <NotFoundH1>{t('404')}</NotFoundH1>
}

export default NotFound;