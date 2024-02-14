import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ProgressBar from '@ramonak/react-progress-bar';
import '../../assets/css/progressBar.css';

const CategoryItem = ({ item }) => {
    const [randomNumber, setRandomNumber] = useState(0);

    const getRandomNumber = () => {
        // 이미지 파일의 총 개수에 맞게 무작위 숫자 생성
        const maxNumber = 26; // 예를 들어 이미지 파일이 1부터 10까지 있다고 가정
        const random = Math.floor(Math.random() * maxNumber) + 1;
        setRandomNumber(random);
    };

    useEffect(() => {
        getRandomNumber();
    }, []);

    return (
        // <div className="flex justify-between mx-2.5 bg-[#E5F1FF] my-3 rounded-lg p-2">
        <div className="flex justify-between mx-2.5 bg-[#e6f1fd] my-3 rounded-lg p-2">
            <div className="flex items-center w-16 h-16 my-3 rounded-full bg-slate-200">
                <img
                    src={`assets/image/iconImage${randomNumber}.png`}
                    alt="icon"
                />
            </div>
            <Link
                to={`/category/detail`}
                state={{ categoryId: item.id, categoryType: item.content }}
                className="w-64 mt-2"
            >
                <p className="flex justify-between">
                    <p className="font-semibold text-left w-44">
                        💠 {item.content}
                    </p>
                    <p className="text-rigth">{item.totalMoney}원</p>
                </p>
                <p className="px-1 py-0.5 mt-0.5 text-left">
                    {item.currentMoney}원
                </p>
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
