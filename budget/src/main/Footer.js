import React from 'react';
import '../assets/css/footer.css';
import iconHome from '../assets/image/icon home.png';
import iconFilter from '../assets/image/icon filter.png';
import iconAdd from '../assets/image/icon add.png';
import iconMonth from '../assets/image/icon month.png';
import iconPlan from '../assets/image/icon plan.png';
import { Link } from 'react-router-dom';

const Footer = () => {
    return (
        <div className="footer-css">
            <div className="flex justify-between mx-5 mt-3">
                <Link to={'/home'}>
                    <img
                        className="w-auto h-10"
                        src={iconHome}
                        alt="iconHome"
                    ></img>
                </Link>
                <Link to={'/category'}>
                    <img
                        className="w-auto h-10"
                        src={iconFilter}
                        alt="iconFilter"
                    ></img>
                </Link>
                <Link to={'/write'}>
                    <img
                        className="w-auto h-10"
                        src={iconAdd}
                        alt="iconAdd"
                    ></img>
                </Link>
                <Link to={'/calender'}>
                    <img
                        className="w-auto h-10"
                        src={iconMonth}
                        alt="iconMonth"
                    ></img>
                </Link>
                <Link>
                    <img
                        className="w-auto h-10"
                        src={iconPlan}
                        alt="iconPlan"
                    ></img>
                </Link>
            </div>
        </div>
    );
};

export default Footer;
