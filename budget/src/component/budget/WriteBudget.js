import React, { useEffect, useState } from 'react';
import axios_api from '../../config/Axios';
import { useNavigate } from 'react-router-dom';

const WriteBudget = () => {
    // 전달 인자
    const navigate = useNavigate();

    const inputFields = [];

    const [categoryList, setCategoryList] = useState([]);

    const [inputs, setInputs] = useState([]);

    const formatDate = (date) => {
        let month = date.getMonth() + 1;
        const year = date.getFullYear();

        if (month.length < 2) month = '0' + month;

        return [year, month];
    };

    useEffect(() => {
        const dateArray = formatDate(new Date());

        axios_api
            .get(`budget/check/${dateArray[0]}/${dateArray[1]}`)
            .then(({ data }) => {
                if (!data.checkBudgetWrite) {
                    alert('이미 예산이 작성되었습니다.');
                    navigate(`/category`, {
                        replace: true,
                    });
                } else {
                    axios_api
                        .get('/budget/category')
                        .then(({ data }) => {
                            setCategoryList(data);
                        })
                        .catch();
                }
            })
            .catch(({ error }) => {
                console.log('예산 작성 여부 확인 중 오류 : ' + error);
            });
    }, []);

    const handleChange = (index, event) => {
        const newInputs = [...inputs];
        newInputs[index] = event.target.value;
        setInputs(newInputs);
    };

    // input 모든 값 입력 확인
    const handleSubmit = () => {
        let allInputsFilled = inputs.every(
            (input) => input !== undefined && input !== ''
        );

        if (inputs.length !== categoryList.length) allInputsFilled = false;

        if (!allInputsFilled) {
            // 값이 안 채워진 경우
        } else {
        }
    };

    for (let i = 0; i < categoryList.length; i++) {
        inputFields.push(
            <div className="flex my-3" key={i}>
                <p className="m-1.5">
                    <span className="px-1 font-semibold text-left">
                        🔸 {categoryList[i].content}
                    </span>
                    <span className="text-left">:</span>
                </p>
                <input
                    type="number"
                    className="p-1 px-2 border border-separate rounded-lg"
                    onChange={(event) =>
                        handleChange(i, event, event.target.value)
                    }
                    placeholder={categoryList[i].content}
                />
            </div>
        );
    }

    return (
        <div>
            {/* <h1 className="text-2xl font-bold text-center">한달 예산 작성</h1> */}
            <p className="mb-2 text-sm">
                한 달 동안 쓸 예산을 카테고리 별로 작성해주세요.
            </p>
            <hr />
            {inputFields}
            <span
                className="p-1 mx-2 font-semibold bg-blue-100 border-2 border-blue-400 rounded-md cursor-default hover:bg-blue-500"
                onClick={handleSubmit}
            >
                작성하기
            </span>
        </div>
    );
};

export default WriteBudget;
