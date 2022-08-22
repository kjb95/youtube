import {useTranslation} from 'react-i18next';

const SignUpErrorMessageList = ({validateSignUp}) => {
	const {t} = useTranslation();

	return validateSignUp.map((errMessage, index) => {
		return <li key={index}>
			<div className='invalidInput'>
				{t(`${errMessage}`)}
			</div>
		</li>;
	});
};

export default SignUpErrorMessageList;