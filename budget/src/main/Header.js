import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../assets/css/header.css';

const Header = () => {
    const { pathname } = useLocation();
    const [pathStateContent, setPathStateContent] = useState();
    const [pathStateImage, setPathStateImage] = useState();

    useEffect(() => {
        if (pathname === '/') {
        } else if (pathname === '/home') {
            setPathStateContent('Money! 부자되기!');
            console.log(pathname);
        } else if (pathname === '/write') {
            setPathStateContent('가계부 작성하기');
        } else if (pathname === '/read') {
            setPathStateContent('가계부 상세 내역 보기');
        } else if (pathname === '/update') {
            setPathStateContent('가계부 수정하기');
        } else if (pathname === '/budget') {
            setPathStateContent('한 달 예산 작성하기');
        } else if (pathname === '/category') {
            setPathStateContent('카테고리 별 금액 모아보기');
        } else if (pathname === '/category/detail') {
            setPathStateContent('카테고리 별 사용 내역 모아보기');
        } else if (pathname === '/calender') {
            setPathStateContent('날짜 별 금액 모아보기');
        }
    }, [pathname]);

    return (
        <div className="header-css">
            <Link to={'/home'}>
                <img
                    className="header-image"
                    src="assets/image/headerMoney.png"
                    alt="headerMoney"
                ></img>
            </Link>
            <div className="header-text-1">
                <div className="header-text-2">{pathStateContent}</div>
            </div>
        </div>
    );
};

export default Header;
