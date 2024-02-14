import React from 'react';
import { Link } from 'react-router-dom';
import '../assets/css/footer.css';

const Footer = () => {
    return (
        <div className="footer-css">
            <div className="flex justify-between mx-5 mt-3">
                <Link to={'/home'}>
                    <img
                        className="w-auto h-10"
                        src="assets/image/icon home.png"
                        alt="iconHome"
                    ></img>
                </Link>
                <Link to={'/category'}>
                    <img
                        className="w-auto h-10"
                        src="assets/image/icon filter.png"
                        alt="iconFilter"
                    ></img>
                </Link>
                <Link to={'/write'}>
                    <img
                        className="w-auto h-10"
                        src="assets/image/icon add.png"
                        alt="iconAdd"
                    ></img>
                </Link>
                <Link to={'/calender'}>
                    <img
                        className="w-auto h-10"
                        src="assets/image/icon month.png"
                        alt="iconMonth"
                    ></img>
                </Link>
                <Link to={'/budget'}>
                    <img
                        className="w-auto h-10"
                        src="assets/image/icon plan.png"
                        alt="iconPlan"
                    ></img>
                </Link>
            </div>
        </div>
    );
};

export default Footer;
