import React, { useEffect, useState } from 'react';
import DatePicker from 'react-datepicker';
import axios_api from '../../config/Axios.js';
import DateItem from './DateItem.js';
import { ko } from 'date-fns/locale';
import 'react-datepicker/dist/react-datepicker.css';
import '../../assets/css/calender.css';

const Calender = () => {
    const [selectDate, setSelectDate] = useState(new Date()); // 날짜 선택
    const [accountList, setAccountList] = useState([]);

    const formatDate = (date) => {
        const d = new Date(date);
        let month = d.getMonth() + 1;
        let day = d.getDate();
        const year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day];
    };

    const handleSelectDate = (date) => {
        setSelectDate(date);
        const dateArray = formatDate(date);
        axios_api
            .get(`account/${dateArray[0]}/${dateArray[1]}/${dateArray[2]}`)
            .then(({ data }) => {
                setAccountList(data);
            })
            .catch(({ error }) => {
                console.log('가계부 날짜별 읽기 중 오류 : ' + error);
            });
    };

    useEffect(() => {
        const dateArray = formatDate(new Date());
        axios_api
            .get(`account/${dateArray[0]}/${dateArray[1]}/${dateArray[2]}`)
            .then(({ data }) => {
                setAccountList(data);
            })
            .catch(({ error }) => {
                console.log('가계부 날짜별 읽기 중 오류 : ' + error);
            });
    }, []);
    // }, [selectDate]);

    return (
        <div>
            <div>
                <DatePicker
                    inline
                    selected={selectDate ? new Date(selectDate) : null}
                    onChange={(date) => handleSelectDate(date)}
                    // onChange={(date) => setSelectDate(date)}
                    maxDate={new Date()}
                    dateFormat="yyyy년 MM월 dd일"
                    locale={ko}
                />
            </div>
            {accountList.map((accountItem) => (
                <DateItem key={accountItem.id} item={accountItem}></DateItem>
            ))}
        </div>
    );
};

export default Calender;
