import 'bootstrap/dist/css/bootstrap.min.css';
import './HomePage.css';
import HeaderComponent from "../../components/molecules/HeaderComponents.tsx";
import Body from "../../components/molecules/Body.tsx";

function HomePage(){
    return(
        <div className="container-fluid">
                <HeaderComponent />
                <Body />
        </div>

    )
}
export default HomePage