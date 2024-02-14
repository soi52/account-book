import './App.css';
import { MobileView } from 'react-device-detect';
import { useLocation } from 'react-router-dom';
import Main from './main/Main';
import Header from './main/Header';
import Footer from './main/Footer';
import './index.css';
import '../src/assets/css/main.css';

function App() {
    const location = useLocation();

    return (
        <div className="App">
            <MobileView>
                <div>
                    {/* Header */}
                    {location.pathname !== '/' && <Header />}
                    <Main />
                    {/* Footer */}
                    {location.pathname !== '/' && <Footer />}
                </div>
            </MobileView>
        </div>
    );
}

export default App;
