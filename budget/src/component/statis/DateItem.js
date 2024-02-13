import React from 'react';
import image1 from '../../assets/image/iconImage1.png';
import { Link } from 'react-router-dom';

const DateItem = ({ item }) => {
    return (
        <div className="flex justify-between mx-2">
            <div className="flex items-center w-16 h-16 my-1 rounded-full bg-slate-200">
                <img src={image1} alt="icon" />
            </div>
            <Link
                to={`/read`}
                state={{ accountDetailId: item.id }}
                className="mt-3"
            >
                <p className="text-left w-44">{item.content}</p>
                <p className="text-left text-gray-400">{item.memo}</p>
            </Link>
            <p className="w-20 mt-3 text-right">{item.amount}원</p>
        </div>
    );
};

export default DateItem;
