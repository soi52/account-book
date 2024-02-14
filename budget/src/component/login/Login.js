import React from 'react';
import { Link } from 'react-router-dom';
import '../../assets/css/login.css';

const Login = () => {
    return (
        <div className="login-background">
            <img
                className="login-handOk"
                src="assets/image/handOk.png"
                alt="handOK"
            ></img>
            <div className="login-title">가계부</div>
            <Link to="/home">
                <img
                    className="login-kakao"
                    src="assets/image/kakaoLogin.png"
                    alt="kakaoLogin"
                ></img>
            </Link>
        </div>
    );
};

export default Login;
