import React, { useEffect, useState } from 'react';
import axios_api from '../../config/Axios';

const WriteAccountBook = () => {
    // 카테고리
    const [categoryBig, setCategoryBig] = useState([]);
    const [categorySmall, setCategorySmall] = useState([]);

    useEffect(() => {
        axios_api
            .get('account/categoryBig')
            .then(({ data }) => {
                console.log(data);
            })
            .catch(({ error }) => {
                console.log('큰 카테고리 불러오기 오류 : ' + error);
            });
    }, []);

    return (
        <div>
            <h1 className="text-3xl font-bold text-center">가계부 작성</h1>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 사용 내역 ✨</p>
                <p className="m-1 text-left">💠 사용처</p>
                <input
                    type="text"
                    maxLength="10"
                    id="usage"
                    className="p-1 m-0.5 rounded-lg"
                    placeholder="사용하신 용도를 적어주세요"
                ></input>
                <p className="m-1 text-left">🔹 금액</p>
                <input
                    type="number"
                    id="amount"
                    className="p-1 m-0.5 rounded-lg"
                    placeholder="사용하신 금액을 적어주세요"
                ></input>
                원<p className="m-1 text-left">🔸 메모</p>
                <input
                    type="text"
                    maxLength="15"
                    id="memo"
                    className="p-1 m-0.5 rounded-lg"
                    placeholder="추가할 메모를 적어주세요"
                ></input>
            </div>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 카테고리 ✨</p>
                <p className="m-1 text-left">🔹 큰 카테고리</p>
                <p className="m-1 text-left">🔸 작은 카테고리</p>
            </div>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 날짜 선택 ✨</p>
            </div>
        </div>
    );
};

export default WriteAccountBook;
