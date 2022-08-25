import {useTranslation} from 'react-i18next';

const SignUpErrorMessageList = ({signUpErrorMessageList}) => {
	const {t} = useTranslation();

	return signUpErrorMessageList.map((errMessage, index) => {
		return <li key={index}>
			<div className='invalidInput'>
				{t(`${errMessage}`)}
			</div>
		</li>;
	});
};

export default SignUpErrorMessageList;