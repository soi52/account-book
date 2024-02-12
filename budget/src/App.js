import './App.css';
import { MobileView } from 'react-device-detect'
import Main from './main/Main';
import Header from './main/Header';
import Footer from './main/Footer';

function App() {
  return (
    <div className="App">
      <MobileView>
        <Header/>
        <Main/>
        <Footer/>
      </MobileView>
    </div>
  );
}

export default App;
