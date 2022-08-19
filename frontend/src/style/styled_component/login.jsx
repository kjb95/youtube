import styled from "styled-components";

export const LoginBox = styled.div`
      body{
         margin:0 auto;
      }
      body, table, div, p ,span{font-family:'Noto Sans KR';}
      a{
          text-decoration: none;
          color:#333;
      }
      #con{
          width:100%;
          height: 100vh;
          background-position: center center;
          background-repeat: no-repeat;
          background-size:cover;
          padding:0;
          white-space: nowrap;
      }
      #login{
          width:600px;
          height: 600px;
          margin:0 auto;
      }
      #login_form{
          border-radius: 10px;
          padding:50px;
          background: #fff;
          position: absolute;
          top:50%;
          left:50%;
          transform: translate(-50%, -50%);
      }
      .login{
          font-size:25px;
          font-weight: 900;
          color:#333;
      }
      .size{
          width:300px;
          height:30px;
          padding-left:10px;
          background-color: #f4f4f4;
          border:none;
          border-radius: 5px;
      }
      .btn{
          width:310px;
          height: 40px;
          font-size:15px;
          color:#fff;
          border:none;
          cursor: pointer;
          border-radius: 5px;
      }
      .btn:hover{
          background:#ca296a;
      }
      hr{
          margin-top:20px;
          background:#eee;
          
      }
      .find{
          color:#ddd;
          font-size:12px;
      }
      .find span{
          padding-left:10px;
      }
      .find span::before{
          content:'|';
          color:#333;
          padding-right: 10px;
      }
      .find span:nth-child(1):before{
          content:none;
      }
      
      .find span:nth-child(1){
          padding-left:0px;
      }
      
      .find a:hover{
          color:#707070;
      }
`

export const SignUpBox = styled.div`
      body{
         margin:0 auto;
      }
      body, table, div, p ,span{font-family:'Noto Sans KR';}
      a{
          text-decoration: none;
          color:#333;
      }
      #con{
          width:100%;
          height: 100vh;
          background-position: center center;
          background-repeat: no-repeat;
          background-size:cover;
          padding:0;
          white-space: nowrap;
      }
      #login{
          width:600px;
          height: 600px;
          margin:0 auto;
      }
      #login_form{
          border-radius: 10px;
          padding:30px 50px;
          background: #fff;
          position: absolute;
          top:50%;
          left:50%;
          transform: translate(-50%, -50%);
      }
      .login{
          font-size:25px;
          font-weight: 900;
          color:#333;
      }
      .size{
          width:300px;
          height:30px;
          padding-left:10px;
          background-color: #f4f4f4;
          border:none;
          border-radius: 5px;
      }
      .btn{
          width:310px;
          height: 40px;
          font-size:15px;
          background-color: #df3278;
          color:#fff;
          border:none;
          cursor: pointer;
          border-radius: 5px;
      }
      .btn:hover{
          background:#ca296a;
      }
      hr{
          margin-top:20px;
          background:#eee;
      }
      .find{
          color:#ddd;
          font-size:12px;
      }
      .find span{
          padding-left:10px;
      }
      .find span::before{
          content:'|';
          color:#333;
          padding-right: 10px;
      }
      .find span:nth-child(1):before{
          content:none;
      }
      
      .find span:nth-child(1){
          padding-left:0px;
      }
      .find a:hover{
          color:#707070;
      }
      .num1{
          width:60px;
          text-align: left;
      }
      .num2{
          width:235px;
      }
      .invalidInput{
        font-size: small;
        color: red;
      }
`

export const LoginTitleH3 = styled.h3`
  letter-spacing:-1px;
  text-align: center;
`
export const GoogleLoginInput = styled.input`
  background-color:#217Af0;
`
export const LoginInput = styled.input`
  background-color:#df3278;
`
export const LoginInformationP = styled.p`
  text-align: left; 
  font-size:12px; 
  color:#666
`