import {GoogleLoginInput, LoginBox, LoginInformationP, LoginInput, LoginTitleH3} from '../style/styledComponent/login.jsx';
import {useTranslation} from 'react-i18next';

function Login() {
	const {t} = useTranslation();

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
						<label> <LoginInformationP>{t('id')} </LoginInformationP>
							<input type='text' placeholder={t('idInput')} className='size' />
							<p />
						</label> <label>
						<LoginInformationP>{t('password')} </LoginInformationP>
						<input type='text' placeholder={t('passwordInput')} className='size' />
					</label>
						<p>
							<LoginInput type='submit' className='btn' value={t('login')} />
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