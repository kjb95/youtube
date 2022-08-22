import axios from 'axios';

/**
 * 회원가입의 계정 생성 버튼에 대한 이벤트 함수
 * @param id 유저 아이디
 * @param password 유저 비밀번호
 * @param nickname 유저 닉네임
 * @param setValidateSignUp 회원가입 실패시 생기는 에러메시지 상태값을 변경해 주는 함수 (useState)
 * @param signUpSuccess 회원가입 성공시 보여주는 메시지
 */
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
				else {
					setValidateSignUp(res.errorMessageList);
				}
			})
}