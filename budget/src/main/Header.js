import React from 'react';
import '../assets/css/header.css';
import headerMoney from '../assets/image/headerMoney.png';

const Header = () => {
    return (
        <div className="header-css">
            <img
                className="header-image"
                src={headerMoney}
                alt="headerMoney"
            ></img>
            <div className="header-text-1">
                <div className="header-text-2">Money! 부자되기!</div>
            </div>
        </div>
    );
};

export default Header;
