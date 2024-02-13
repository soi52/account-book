import { Routes, Route } from 'react-router-dom';
import Login from '../component/login/Login';
import Home from '../component/statis/Home';
import WriteAccountBook from '../component/accountBook/WriteAccountBook';
import ReadAccountBook from '../component/accountBook/ReadAccountBook';
import UpdateAccountBook from '../component/accountBook/UpdateAccountBook';
import WriteBudget from '../component/budget/WriteBudget';
import Category from '../component/statis/Category';
import Calender from '../component/statis/Calender';
import '../assets/css/main.css';

const Main = () => {
    return (
        <div className="AppMain">
            <Routes>
                <Route path="/" element={<Login />}></Route>
                <Route path="/home" element={<Home />}></Route>
                <Route path="/write" element={<WriteAccountBook />}></Route>
                <Route path="/read" element={<ReadAccountBook />}></Route>
                <Route path="/update" element={<UpdateAccountBook />}></Route>
                <Route path="/budget" element={<WriteBudget />}></Route>
                <Route path="/category" element={<Category />}></Route>
                <Route path="/calender" element={<Calender />}></Route>
            </Routes>
        </div>
    );
};

export default Main;
