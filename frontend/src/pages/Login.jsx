import {GoogleLoginInput, LoginBox, LoginInformationP, LoginInput, LoginTitleH3} from '../style/styledComponent/login.jsx';
import {useTranslation} from 'react-i18next';
import {login} from '../service/login/login.jsx';
import {useState} from 'react';

function Login() {
	const {t} = useTranslation();
	const [id, setId] = useState('');
	const [password, setPassword] = useState('');

	const [loginErrorMessage, setLoginErrorMessage] = useState(undefined);

	return <LoginBox>
		<div id='con'>
			<div id='login'>
				<div id='login_form'>
					<form>
						<LoginTitleH3 className='login'>{t('login')}</LoginTitleH3>
						<p>
							<GoogleLoginInput type='submit' className='btn' value={t('googleLogin')} />
						</p>
						<hr />
						{loginErrorMessage &&
								<div className='invalidInput'>{t(loginErrorMessage)}</div>}
						<label> <LoginInformationP>{t('id')} </LoginInformationP>
							<input type='text' placeholder={t('idInput')} className='size' value={id} onChange={event => setId(event.target.value)} />
							<p />
						</label> <label>
						<LoginInformationP>{t('password')} </LoginInformationP>
						<input type='password' placeholder={t('passwordInput')} className='size' value={password} onChange={event => setPassword(event.target.value)} />
					</label>
						<p>
							<LoginInput type='button' className='btn' value={t('login')} onClick={() => {
								login(id, password, setLoginErrorMessage);
							}} />
						</p>
					</form>
					<hr />
					<p className='find'>
						<span><a href='/sign-up'>{t('signUp')}</a></span>
					</p>
				</div>
			</div>
		</div>
	</LoginBox>;
}

export default Login;