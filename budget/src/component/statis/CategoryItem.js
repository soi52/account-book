import React from 'react';
import { Link } from 'react-router-dom';
import ProgressBar from '@ramonak/react-progress-bar';
import '../../assets/css/progressBar.css';
import image1 from '../../assets/image/iconImage1.png';

const CategoryItem = ({ item }) => {
    return (
        <div className="flex justify-between mx-2 bg-[#E5F1FF] my-3 rounded-lg p-2">
            <div className="flex items-center w-16 h-16 my-3 rounded-full bg-slate-200">
                <img src={image1} alt="icon" />
            </div>
            <Link
                to={`/read`}
                state={{ accountDetailId: item.id }}
                className="w-64 mt-3"
            >
                <p className="text-left w-44">{item.content}</p>
                <p className="w-20 mt-0.5 text-left">{item.currentMoney}원</p>
                <div className="">
                    <ProgressBar
                        completed={(item.currentMoney / item.totalMoney) * 100}
                        className="mx-1"
                        // barContainerClassName="container"
                        // completedClassName="barCompleted"
                        // labelClassName="label"
                    />
                </div>
            </Link>
        </div>
    );
};

export default CategoryItem;
