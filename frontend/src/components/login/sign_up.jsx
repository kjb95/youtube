import axios from 'axios';

export const createAccount = async (id, password, nickname, setValidateSignUp, signUpSuccess) => {
	const account = {
		id: id,
		password: password,
		nickname: nickname
	}

	await axios.post('http://localhost:8080/api/users', account)
						 .then(res => {
							 res = res.data;
							 if (res.success === true) {
								 alert(signUpSuccess);
								 window.location.href = '/login';
							 }
							 else setValidateSignUp(res.errorMessageList);
						 })
}