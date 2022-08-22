import {LoginInformationP, LoginTitleH3, SignUpBox} from "../style/styled_component/login.jsx";
import {useTranslation} from "react-i18next";
import {useEffect, useState} from "react";
import {createAccount} from "../service/videPlay/signUp.jsx";
import SignUpErrorMessageList from "../components/login/SignUpErrorMessageList.jsx";

function SignUp() {
	const {t} = useTranslation();

	const [id, setId] = useState('');
	const [password, setPassword] = useState('');
	const [confirmPassword, setConfirmPassword] = useState('');
	const [nickname, setNickname] = useState('');

	const [validateId, setValidateId] = useState(false);
	const [validatePassword, setValidatePassword] = useState(false);
	const [validateConfirmPassword, setValidateConfirmPassword] = useState(false);
	const [validateNickname, setValidateNickname] = useState(false);
	const [isSamePassword, setIsSamePassword] = useState(false);
	const [validateSignUp, setValidateSignUp] = useState(undefined);

	useEffect(() => {
		const regexp = /^[a-zA-Z0-9]{8,20}$/;
		if (regexp.test(id)) setValidateId(true); else setValidateId(false);
		if (regexp.test(password)) setValidatePassword(true); else setValidatePassword(false);
		if (regexp.test(confirmPassword)) setValidateConfirmPassword(true); else setValidateConfirmPassword(false);
		if (regexp.test(nickname)) setValidateNickname(true); else setValidateNickname(false);
		if (password === confirmPassword) setIsSamePassword(true); else setIsSamePassword(false);
	}, [id, password, confirmPassword, nickname, validateSignUp])

	return <SignUpBox>
		<div id='con'>
			<div id='login'>
				<div id='login_form'>
					<form>
						<LoginTitleH3 className='login'>{t('signUp')}</LoginTitleH3>
						<hr />
						<label> <LoginInformationP>{t('id')}</LoginInformationP>
							<input type='text' placeholder={t('idInput')} className='size' value={id} onChange={event => setId(event.target.value)} /> {!validateId &&
									<div className='invalidInput'>{t('invalidInput')}</div>} {/*<p />*/}
						</label> <label>
						<LoginInformationP>{t('password')} </LoginInformationP>
						<input type='password' placeholder={t('passwordInput')} className='size' value={password} onChange={event => setPassword(event.target.value)} /> {!validatePassword &&
							<div className='invalidInput'>{t('invalidInput')}</div>} {/*<p />*/}
					</label> <label>
						<LoginInformationP>{t('confirmPassword')} </LoginInformationP>
						<input type='password' placeholder={t('confirmPasswordInput')} className='size' value={confirmPassword} onChange={event => setConfirmPassword(event.target.value)} /> {!validateConfirmPassword &&
							<div className='invalidInput'>{t('invalidInput')}</div>} {validateConfirmPassword && !isSamePassword &&
							<div className='invalidInput'>{t('differentPassword')}</div>}
					</label> <label>
						<LoginInformationP>{t('nickname')} </LoginInformationP>
						<input type='text' placeholder={t('nicknameInput')} className='size' value={nickname} onChange={event => setNickname(event.target.value)} /> {!validateNickname &&
							<div className='invalidInput'>{t('invalidInput')}</div>}
					</label>
					</form>
					<br />
					<div>
						{validateId && validatePassword && validateConfirmPassword && validateNickname &&
								<input type='button' value={t('createAccount')} className='btn' onClick={() => {
									createAccount(id, password, nickname, setValidateSignUp, t('signUpSuccess'))
								}} />} {validateSignUp &&
							<SignUpErrorMessageList validateSignUp={validateSignUp} />}
					</div>
					<hr />
					<p className='find'>
						<span><a href='/login'>{t('goToLoginPage')}</a></span>
					</p>
				</div>
			</div>
		</div>
	</SignUpBox>
}

export default SignUp;