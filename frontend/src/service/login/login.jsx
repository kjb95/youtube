import axios from 'axios';

/**
 * 로그인
 *
 * @param id 로그인 아이디
 * @param password 로그인 비밀번호
 */
export const login = async (id, password, setLoginErrorMessage) => {
	const loginData = {
		id: id,
		password: password
	}
	await axios.post('http://localhost:8080/api/auth/login', loginData)
			.then(res => {
				res = res.data;
				if (res.success === true) {
					window.location.href = '/';
				}
				else {
					setLoginErrorMessage(res.errorMessage);
				}
			});
};