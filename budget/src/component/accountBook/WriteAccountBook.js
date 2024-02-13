import React, { useEffect, useState } from 'react';
import axios_api from '../../config/Axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const WriteAccountBook = () => {
    // 사용 내역
    const [content, setContent] = useState(); // 사용처
    const [amount, setAmount] = useState(); // 금액
    const [memo, setMemo] = useState(); // 메모
    // 카테고리
    const [categoryBig, setCategoryBig] = useState([]);
    const [selectCateBig, setSelectCateBig] = useState(null);
    const [categorySmall, setCategorySmall] = useState([]);
    const [selectCateSmall, setSelectCateSmall] = useState(null);
    // 날짜 선택
    const [selectDate, setSelectDate] = useState(new Date());

    // 큰 카테고리 선택 이벤트 함수
    const handleCateBigClick = (index) => {
        setSelectCateBig(index);

        axios_api
            .get(`account/categorySmall?cateBig=${categoryBig[index]}`)
            .then(({ data }) => {
                setCategorySmall(data);
            })
            .catch(({ error }) => {
                console.log('작은 카테고리 불러오기 오류 : ' + error);
            });

        setSelectCateSmall(null);
    };

    // 작은 카테고리 선택 이벤트 함수
    const handleCateSmallClick = (index) => {
        setSelectCateSmall(index);
    };

    // 날짜를 YYYY-MM-DD 형식으로 변환하는 함수
    const formatDate = (date) => {
        const d = new Date(date);
        let month = '' + (d.getMonth() + 1);
        let day = '' + d.getDate();
        const year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    };

    // 날짜 선택 시 selectDate 상태 업데이트
    const handleDateChange = (date) => {
        setSelectDate(formatDate(date));
    };

    const checkValue = () => {
        // 모든 입력값이 존재하는지 검증
        if (
            !content ||
            !amount ||
            selectCateBig === null ||
            selectCateSmall === null ||
            !selectDate
        ) {
            alert('모든 필수 항목을 입력해주세요.\n메모는 선택 사항입니다.');
        } else {
            handleSubmit();
        }
    };

    const handleSubmit = () => {
        axios_api
            .post('account', {
                content: content,
                amount: amount,
                date: selectDate,
                categoryId: selectCateSmall,
                categoryType: categoryBig[selectCateBig],
                ...(memo !== null && { memo: memo }),
            })
            .then(() => {})
            .catch(({ error }) => {
                console.log('가계부 작성 중 오류 : ' + error);
            });
    };

    useEffect(() => {
        // 큰 카테고리 불러오기
        axios_api
            .get('account/categoryBig')
            // .then((response) => {
            //     console.log(response.data);
            //     console.log(response.status);
            // })
            .then(({ data }) => {
                setCategoryBig(data);
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
                    onChange={(event) => {
                        setContent(event.target.value);
                    }}
                ></input>
                <p className="m-1 text-left">🔹 금액</p>
                <input
                    type="number"
                    id="amount"
                    className="p-1 m-0.5 rounded-lg"
                    placeholder="사용하신 금액을 적어주세요"
                    onChange={(event) => {
                        setAmount(event.target.value);
                    }}
                ></input>
                원<p className="m-1 text-left">🔸 메모</p>
                <input
                    type="text"
                    maxLength="15"
                    id="memo"
                    className="p-1 m-0.5 rounded-lg"
                    placeholder="추가할 메모를 적어주세요"
                    onChange={(event) => {
                        setMemo(event.target.value);
                    }}
                ></input>
            </div>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 카테고리 ✨</p>
                <p className="m-1 text-left">🔹 큰 카테고리</p>
                {categoryBig.map((string, index) => (
                    <span
                        key={index}
                        onClick={() => handleCateBigClick(index)}
                        className={`cursor-default p-0.5 mx-2 hover:bg-cyan-200 border-gray border-2 border-dashed rounded-md ${selectCateBig === index ? 'bg-cyan-200' : ''}`}
                    >
                        {string}
                    </span> // 리스트를 순회하며 각 항목을 렌더링
                ))}
                <p className="m-1 text-left">🔸 작은 카테고리</p>
                {categorySmall.map(({ content, id }) => (
                    <span
                        key={id}
                        onClick={() => handleCateSmallClick(id)}
                        className={`cursor-default p-0.5 mx-2 my-1 hover:bg-cyan-200 border-gray border-2 border-dashed rounded-md ${selectCateSmall === id ? 'bg-cyan-200' : ''}`}
                    >
                        {content}
                    </span> // 리스트를 순회하며 각 항목을 렌더링
                ))}
            </div>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 날짜 선택 ✨</p>
                <DatePicker
                    // selected={selectDate}
                    // onChange={(date) => setSelectDate(date)}
                    selected={selectDate ? new Date(selectDate) : null}
                    onChange={handleDateChange}
                    dateFormat="yyyy년 MM월 dd일"
                    maxDate={new Date()}
                    // isClearable
                    // showYearDropdown
                    // scrollableMonthYearDropdown
                />
            </div>
            <div>
                <span
                    className="p-1 mx-2 border border-black rounded-md cursor-default hover:bg-green-400"
                    onClick={() => checkValue()}
                >
                    작성하기
                </span>
            </div>
        </div>
    );
};

export default WriteAccountBook;
