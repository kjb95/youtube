import {
  LoginInformationP,
  LoginTitleH3, SignUpBox
} from "../../style/styled_component/login.jsx";
import {useTranslation} from "react-i18next";

function SignUp() {
  const { t } = useTranslation();

  return <SignUpBox>
    <div id="con">
      <div id="login">
        <div id="login_form">
          <form>
            <LoginTitleH3 className="login">{t('signUp')}</LoginTitleH3>
            <hr/>
            <label>
              <LoginInformationP>{t('id')} </LoginInformationP>
              <input type="text" placeholder={t('idInput')} className="size"/>
              <p></p>
            </label>
            <label>
              <LoginInformationP>{t('password')} </LoginInformationP>
              <input type="text" placeholder={t('passwordInput')} className="size"/>
              <p/>
            </label>
            <label>
              <input type="password" placeholder={t('confirmPassword')} class="size"/>
            </label>
            <label>
              <LoginInformationP>{t('nickname')} </LoginInformationP>
              <input type="text" placeholder={t('nicknameInput')} className="size"/>
            </label>
            <br/>
            <p>
              <input type="submit" value={t('createAccount')} class="btn"/>
            </p>
          </form>
          <hr/>
          <p class="find">
            <span><a href="/login">{t('goToLoginPage')}</a></span>
          </p>
        </div>
      </div>
    </div>
  </SignUpBox>
}

export default SignUp;