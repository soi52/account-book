import { Routes, Route } from 'react-router-dom';
import Login from '../component/login/Login';
import WriteAccountBook from '../component/accountBook/WriteAccountBook';
import ReadAccountBook from '../component/accountBook/ReadAccountBook';
import Calender from '../component/statis/Calender';
import '../assets/css/main.css';

const Main = () => {
    return (
        <div className="AppMain">
            <Routes>
                <Route path="/login" element={<Login />}></Route>
                <Route path="/write" element={<WriteAccountBook />}></Route>
                <Route path="/read" element={<ReadAccountBook />}></Route>
                <Route path="/calender" element={<Calender />}></Route>
            </Routes>
        </div>
    );
};

export default Main;
