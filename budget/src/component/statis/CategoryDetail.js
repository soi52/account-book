import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import DateItem from './DateItem';
import axios_api from '../../config/Axios';

const CategoryDetail = () => {
    // 전달 인자
    const { state } = useLocation();

    const [categoryType, setCategoryType] = useState();
    const [accountList, setAccountList] = useState([]);

    const formatDate = (date) => {
        let year = date.getFullYear();
        let month = date.getMonth() + 1;

        if (month.length < 2) month = '0' + month;

        return [year, month];
    };

    useEffect(() => {
        const dateArray = formatDate(new Date());

        setCategoryType(state.categoryType);

        axios_api
            .get(
                `account/category/${state.categoryId}/${dateArray[0]}/${dateArray[1]}`
            )
            .then(({ data }) => {
                setAccountList(data);
            })
            .catch(({ error }) => {
                console.log(
                    '가계부 카테고리 별 사용 내역 읽기 중 오류 : ' + error
                );
            });
    }, []);

    return (
        <div>
            {/* <h1 className="text-2xl font-bold text-center">
                카테고리 별 사용 내역
            </h1> */}
            <h1 className="text-xl font-bold text-center">
                {categoryType}에 사용한 내역
            </h1>
            <p className="mb-2 my-0.5 text-sm">
                한 달 동안 쓴 카테고리 별 사용 내역에요.
            </p>
            <hr className="mt-1 mb-2" />
            {accountList.map((accountItem) => (
                <DateItem key={accountItem.id} item={accountItem}></DateItem>
            ))}
        </div>
    );
};

export default CategoryDetail;
