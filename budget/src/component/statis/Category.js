import React, { useEffect, useState } from 'react';
import axios_api from '../../config/Axios.js';
import CategoryItem from './CategoryItem';

const Category = () => {
    const [budgetList, setBudgetList] = useState([]);

    const formatDate = (date) => {
        let month = date.getMonth() + 1;
        const year = date.getFullYear();

        if (month.length < 2) month = '0' + month;

        return [year, month];
    };

    useEffect(() => {
        const dateArray = formatDate(new Date());
        axios_api
            .get(`budget/${dateArray[0]}/${dateArray[1]}`)
            .then(({ data }) => {
                setBudgetList(data);
            })
            .catch(({ error }) => {
                console.log('카테고리 예산 읽기 중 오류 : ' + error);
            });
    }, []);
    return (
        <div>
            <h1 className="text-2xl font-bold text-center">
                카테고리 별 사용 내역
            </h1>
            {budgetList.map((budgetItem) => (
                <CategoryItem
                    key={budgetItem.id}
                    item={budgetItem}
                ></CategoryItem>
            ))}
        </div>
    );
};

export default Category;
