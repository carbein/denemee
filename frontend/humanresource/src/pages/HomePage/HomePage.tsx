import 'bootstrap/dist/css/bootstrap.min.css';
import './HomePage.css';
import HeaderComponent from "../../components/molecules/HeaderComponents.tsx";
import Body from "../../components/molecules/Body.tsx";
import FooterComponent from "../../components/molecules/FooterComponents.tsx";

function HomePage(){
    return(
        <div className="container-xxl">
                <HeaderComponent />
                <Body />
                <FooterComponent/>
        </div>

    )
}
export default HomePage