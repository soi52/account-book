import React from 'react';
import '../../assets/css/login.css';
import handOk from '../../assets/image/handOk.png';
import kakaoLogin from '../../assets/image/kakaoLogin.png';
import { Link } from 'react-router-dom';

const Login = () => {
    return (
        <div className="login-background">
            <img className="login-handOk" src={handOk} alt="handOK"></img>
            <div className="login-title">가계부</div>
            <Link to="/write">
                <img
                    className="login-kakao"
                    src={kakaoLogin}
                    alt="kakaoLogin"
                ></img>
            </Link>
        </div>
    );
};

export default Login;
