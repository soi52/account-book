import React, { useEffect, useState } from 'react';
import axios_api from '../../config/Axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { useLocation, useNavigate } from 'react-router-dom';

const UpdateAccountBook = () => {
    const { state } = useLocation();
    const navigate = useNavigate();

    // 가계부 식별
    const [updateId, setUpdateId] = useState(); // 식별자
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
        // 가계부 수정하기
        axios_api
            .put('account', {
                id: updateId,
                content: content,
                amount: amount,
                date: selectDate,
                categoryId: selectCateSmall,
                categoryType: categoryBig[selectCateBig],
                ...(memo !== null && { memo: memo }),
            })
            .then((response) => {
                navigate(`/read`, {
                    state: { accountDetailId: updateId },
                    // replace: true,
                });
            })
            .catch(({ error }) => {
                console.log('가계부 수정 중 오류 : ' + error);
            });
    };

    useEffect(() => {
        setUpdateId(state.detailId);

        // 큰 카테고리 불러오기
        axios_api
            .get('account/categoryBig')
            .then(({ data }) => {
                setCategoryBig(data);
            })
            .catch(({ error }) => {
                console.log('큰 카테고리 불러오기 오류 : ' + error);
            });

        let originCategoryId = 0,
            index = 0;

        // 기존 값 불러오기
        axios_api
            .get(`account/${state.detailId}`)
            .then(({ data }) => {
                setContent(data.content);
                setAmount(data.amount);
                setMemo(data.memo);
                setSelectDate(formatDate(data.date));

                originCategoryId = data.categoryId;

                axios_api
                    .get(`account/category/${originCategoryId}`)
                    .then(({ data }) => {
                        if (data.type === '수입') index = 0;
                        else if (data.type === '저금') index = 1;
                        else index = 2;

                        setSelectCateBig(index);

                        setSelectCateSmall(originCategoryId);
                    })
                    .catch(({ error }) => {
                        console.log('기존 카테고리 보기 오류 : ' + error);
                    });
            })
            .catch(({ error }) => {
                console.log('가계부 상세 보기 오류 : ' + error);
            });

        // 작은 카테고리 불러오기
        axios_api
            .get(`account/categorySmall?cateBig=${categoryBig[index]}`)
            .then(({ data }) => {
                setCategorySmall(data);
                setSelectCateSmall(originCategoryId);
            })
            .catch(({ error }) => {
                console.log('작은 카테고리 불러오기 오류 : ' + error);
            });
    }, []);

    useEffect(() => {
        axios_api
            .get(`account/categorySmall?cateBig=${categoryBig[selectCateBig]}`)
            .then(({ data }) => {
                setCategorySmall(data);
            })
            .catch(({ error }) => {
                console.log('작은 카테고리 불러오기 오류 : ' + error);
            });
        console.log(categorySmall);
    }, [selectCateBig]);

    return (
        <div>
            {/* <h1 className="text-2xl font-bold text-center">가계부 수정</h1> */}
            <div className="box-border p-1 mx-2 mt-5 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 사용 내역 ✨</p>
                <p className="m-1 text-left">💠 사용처</p>
                {/* <p>onMouseOver 함수 실행 - 포커스 실행 </p> */}
                <input
                    type="text"
                    maxLength="10"
                    id="usage"
                    className="p-1 px-2 m-0.5 rounded-lg border text-center"
                    defaultValue={content || ''}
                    onChange={(event) => {
                        setContent(event.target.value);
                    }}
                ></input>
                <p className="m-1 text-left">🔹 금액</p>
                <input
                    type="number"
                    id="amount"
                    className="p-1 m-0.5 w-52 rounded-lg border text-center"
                    defaultValue={amount || ''}
                    placeholder="사용하신 금액을 적어주세요"
                    onChange={(event) => {
                        setAmount(event.target.value);
                    }}
                ></input>
                <span className="ml-1">원</span>
                <p className="m-1 text-left">🔸 메모</p>
                <input
                    type="text"
                    maxLength="15"
                    id="memo"
                    className="p-1 px-2 m-0.5 rounded-lg border text-center"
                    defaultValue={memo || ''}
                    placeholder="추가할 메모를 적어주세요"
                    onChange={(event) => {
                        setMemo(event.target.value);
                    }}
                ></input>
            </div>
            <div className="box-border -1 mx-2 my-2.5 border border-teal-400 border-solid rounded-md">
                <p className="m-1 font-semibold text-left">✨ 카테고리 ✨</p>
                <p className="mx-1 mt-2 mb-1 text-left">🔹 큰 카테고리</p>
                <p className="my-1">
                    {categoryBig.map((string, index) => (
                        <span
                            key={index}
                            onClick={() => {
                                console.log('index', index);
                                handleCateBigClick(index);
                            }}
                            className={`cursor-default p-1 mx-4 hover:bg-cyan-200 border-gray border-2 border-dashed rounded-md ${selectCateBig === index ? 'bg-cyan-400' : ''}`}
                        >
                            {string}
                        </span> // 리스트를 순회하며 각 항목을 렌더링
                    ))}
                </p>
                <p className="mx-1 mt-2 mb-1 text-left">🔸 작은 카테고리</p>
                <p className="my-1">
                    {categorySmall.map(({ content, id }) => (
                        <span
                            key={id}
                            onClick={() => handleCateSmallClick(id)}
                            className={`cursor-default m-2 my-10 hover:bg-cyan-200 border-gray border-2 border-dashed rounded-md ${selectCateSmall === id ? 'bg-cyan-300' : ''}`}
                        >
                            {content}
                        </span> // 리스트를 순회하며 각 항목을 렌더링
                    ))}
                </p>
            </div>
            <div className="box-border p-1 m-2 border border-teal-400 border-solid rounded-md">
                <p className="mx-1 mt-1 mb-2 font-semibold text-left">
                    ✨ 날짜 선택 ✨
                </p>
                <DatePicker
                    // selected={selectDate}
                    // onChange={(date) => setSelectDate(date)}
                    selected={selectDate ? new Date(selectDate) : null}
                    onChange={handleDateChange}
                    dateFormat="yyyy년 MM월 dd일"
                    className="p-0.5 my-1 text-center border rounded-lg"
                    maxDate={new Date()}
                    // isClearable
                    // showYearDropdown
                    // scrollableMonthYearDropdown
                />
            </div>
            <div>
                <span
                    className="p-1 mx-2 font-semibold bg-blue-100 border-2 border-blue-400 rounded-md cursor-default hover:bg-blue-500"
                    onClick={() => checkValue()}
                >
                    수정하기
                </span>
            </div>
        </div>
    );
};

export default UpdateAccountBook;
