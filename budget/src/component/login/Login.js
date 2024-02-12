import React from 'react';
import '../../assets/css/login.css';
import handOk from '../../assets/image/handOk.png';
import kakaoLogin from '../../assets/image/kakaoLogin.png';

const Login = () => {
    return (
        <div className="login-background">
            <img className="login-handOk" src={handOk} alt="handOK"></img>
            <div className="login-title">가계부</div>
            <img
                className="login-kakao"
                src={kakaoLogin}
                alt="kakaoLogin"
            ></img>
        </div>
    );
};

export default Login;
