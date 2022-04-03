import 'bootstrap/dist/css/bootstrap.min.css';

import Header from "./component/header/Header";
import Content from "./component/content/Content";
import Footer from "./component/footer/Footer";


export default  function App() {
  return (
    <div className="App">
        <Header/>
        <Content/>
        <Footer/>
    </div>
  );
}