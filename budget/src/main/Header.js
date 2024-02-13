import React from 'react';
import '../assets/css/header.css';
import headerMoney from '../assets/image/headerMoney.png';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <div className="header-css">
            <Link to={'/home'}>
                <img
                    className="header-image"
                    src={headerMoney}
                    alt="headerMoney"
                ></img>
            </Link>
            <div className="header-text-1">
                <div className="header-text-2">Money! 부자되기!</div>
            </div>
        </div>
    );
};

export default Header;
