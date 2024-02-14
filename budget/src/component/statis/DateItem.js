import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const DateItem = ({ item }) => {
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
        <div className="flex justify-between px-4 py-3 my-2 ml-2 mr-3 border-2 border-indigo-300 rounded-md">
            <div className="flex items-center w-16 my-1 overflow-hidden rounded-full h-14 bg-slate-200">
                <img
                    className="object-cover w-full h-full"
                    src={`../assets/image/iconImage${randomNumber}.png`}
                    alt="icon"
                />
            </div>
            <Link
                to={`/read`}
                state={{ accountDetailId: item.id }}
                className="mt-1.5 ml-2.5"
            >
                <p className="w-40 text-lg text-left">{item.content}</p>
                <p className="pl-1 text-left text-gray-400">{item.memo}</p>
            </Link>
            <p className="w-24 mt-2 text-right">{item.amount}원</p>
        </div>
    );
};

export default DateItem;
