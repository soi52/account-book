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
        <div className="flex justify-between my-2 ml-2 mr-3">
            <div className="flex items-center w-16 h-16 my-1 rounded-full bg-slate-200">
                <img
                    src={`../assets/image/iconImage${randomNumber}.png`}
                    alt="icon"
                />
            </div>
            <Link
                to={`/read`}
                state={{ accountDetailId: item.id }}
                className="my-2"
            >
                <p className="text-lg text-left w-44">{item.content}</p>
                <p className="text-left text-gray-400">{item.memo}</p>
            </Link>
            <p className="w-20 my-2 mt-3 text-right">{item.amount}원</p>
        </div>
    );
};

export default DateItem;
