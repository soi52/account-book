import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import axios_api from '../../config/Axios.js';
import { CircularProgressBar } from '@tomickigrzegorz/react-circular-progress-bar';

const Home = () => {
    const navigate = useNavigate();

    const [currentMoney, setCurrentMoney] = useState();
    const [totalMoney, setTotalMoney] = useState();

    useEffect(() => {
        let date = new Date();

        let year = date.getFullYear();
        let month = date.getMonth() + 1;

        axios_api
            .get(`account/${year}/${month}`)
            .then(({ data }) => {
                setCurrentMoney(data.currentMoney);
                setTotalMoney(data.totalMoney);
            })
            .catch(({ error }) => {
                console.log('월별 통계 조회 중 오류 : ' + error);
            });
    }, []);

    return (
        <div className="mt-16">
            <div className="bg-[#007bff94] p-5 m-2 flex justify-between rounded-xl border-white">
                <div>
                    <p className="m-1 text-xs text-left text-white">
                        오늘 지출을 적으셨나요?
                    </p>
                    <p className="m-1 text-3xl font-semibold text-left text-white">
                        가계부 작성하기!
                    </p>
                </div>
                {/* <div className="bg-[#007bff] filter blur-md rounded-lg m-2 my-4"></div> */}
                <img
                    className="w-6 h-6 ml-1 mr-0.5 my-5"
                    src="assets/image/icon plus.png"
                    alt="icon Plus"
                    onClick={() => {
                        navigate(`/write`);
                    }}
                ></img>
            </div>
            <div className="bg-[#af52de4b] p-2 m-2 rounded-2xl">
                <div className="ml-1.5 mt-1 text-2xl font-semibold text-left">
                    Expenses
                </div>
                <div className="flex justify-between mx-3">
                    <div className="mt-4 ">
                        <div className="p-1 my-3 border-2 rounded-md border-[#DFA0FF]">
                            <p className="text-lg font-semibold">수입 :</p>
                            <p className="">{totalMoney} 원</p>
                        </div>
                        <div className="p-1 my-3 border rounded-md border-[#934CB7]">
                            <p className="text-lg font-semibold">지출 : </p>
                            <p className="">{currentMoney} 원</p>
                        </div>
                    </div>
                    <CircularProgressBar
                        colorCircle="#DFA0FF"
                        colorSlice="#934CB7"
                        percent={(currentMoney / totalMoney) * 100}
                        fontColor="#934CB7"
                        round={true}
                        fontSize="15px"
                        textPosition="1.5rem"
                    ></CircularProgressBar>
                </div>
            </div>
            <div></div>
        </div>
    );
};

export default Home;
