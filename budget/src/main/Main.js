import { Routes, Route } from 'react-router-dom';
import Login from '../component/login/Login';
import WriteAccountBook from '../component/accountBook/WriteAccountBook';
import '../assets/css/main.css';

const Main = () => {
    return (
        <div className="AppMain">
            <Routes>
                <Route path="/login" element={<Login />}></Route>
                <Route path="/write" element={<WriteAccountBook />}></Route>
            </Routes>
        </div>
    );
};

export default Main;
