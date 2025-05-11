import 'bootstrap/dist/css/bootstrap.min.css';
import './HomePage.css';
import HeaderComponent from "../../components/molecules/HeaderComponents.tsx";

function HomePage(){
    return(
        <div className="container-fluid">
            <div className="dark-green text-white d-flex"
                 style={{ height: '600px', flexShrink: 0 }}>
                <HeaderComponent />
            </div>

            <div className="text-dark overflow-auto" style={{ flexGrow: 1 }}>

            </div>
        </div>

    )
}
export default HomePage