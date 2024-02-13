import React, { useEffect, useState } from 'react';
import axios_api from '../../config/Axios';
import { useLocation, useNavigate } from 'react-router-dom';

const ReadAccountBook = () => {
    // 전달 인자
    const { state } = useLocation();
    const navigate = useNavigate();

    // 식별자
    const [detailId, setDetailId] = useState(''); // 사용처
    // 사용 내역
    const [content, setContent] = useState(''); // 사용처
    const [amount, setAmount] = useState(''); // 금액
    const [memo, setMemo] = useState(''); // 메모
    // 카테고리
    const [categorySmall, setCategorySmall] = useState('');
    // 날짜
    const [selectDate, setSelectDate] = useState('');

    function formatDate(timestamp) {
        // 타임스탬프를 Date 객체로 변환
        const date = new Date(timestamp);

        // toLocaleDateString을 사용하여 '년월일' 형식으로 변환
        // 옵션을 설정하여 원하는 형식을 지정 가능
        const dateString = date.toLocaleDateString('ko-KR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
        });
        const dayName = date.toLocaleDateString('ko-KR', {
            weekday: 'long',
        });

        return `${dateString} ${dayName}`;
    }

    useEffect(() => {
        // .get(`account/${state.accountBookId}`)
        axios_api
            .get(`account/${state.accountDetailId}`)
            .then(({ data }) => {
                setDetailId(data.id);
                setContent(data.content);
                setAmount(data.amount);
                setMemo(data.memo);
                setCategorySmall(data.categoryId);
                setSelectDate(formatDate(data.date));
            })
            .catch(({ error }) => {
                console.log('가계부 상세 보기 오류 : ' + error);
            });
    }, []);

    return (
        <div>
            <h1 className="text-3xl font-bold text-center">가계부 작성</h1>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 사용 내역 ✨</p>
                <p className="m-1 text-left">💠 사용처 :</p>
                <p className="p-1 m-0.5 rounded-lg">{content}</p>
                <p className="m-1 text-left">🔹 금액 :</p>
                <p className="p-1 m-0.5 rounded-lg">{amount} 원</p>
                <p className="m-1 text-left">🔸 메모 :</p>
                <p className="p-1 m-0.5 rounded-lg">{memo}</p>
            </div>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 카테고리 ✨</p>
                <p className="p-1 m-0.5 rounded-lg">{categorySmall}</p>
            </div>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 사용 날짜 ✨</p>
                <p className="p-1 m-0.5 rounded-lg">{selectDate}</p>
            </div>
            <div className="mt-4">
                <span className="p-1 mx-2 font-semibold bg-blue-100 border-2 border-blue-400 rounded-md cursor-default hover:bg-blue-500">
                    수정하기
                </span>
                <span className="p-1 mx-2 font-semibold bg-red-100 border-2 border-red-400 rounded-md cursor-default hover:bg-red-500">
                    삭제하기
                </span>
            </div>
        </div>
    );
};

export default ReadAccountBook;
