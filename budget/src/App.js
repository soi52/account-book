import './App.css';
import { MobileView } from 'react-device-detect';
import Main from './main/Main';
import Header from './main/Header';
import Footer from './main/Footer';

function App() {
    return (
        <div className="App">
            <MobileView>
                <div>
                    <Header />
                    <Main />
                    <Footer />
                </div>
            </MobileView>
        </div>
    );
}

export default App;
