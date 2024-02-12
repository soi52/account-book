import { Routes, Route } from 'react-router-dom';
import Login from '../component/login/Login';

const Main = () => {
    return (
        <Routes>
            <Route path="/login" element={<Login />}></Route>
        </Routes>
    );
};

export default Main;
