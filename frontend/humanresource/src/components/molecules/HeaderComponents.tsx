import 'bootstrap/dist/css/bootstrap.min.css';
import './HeaderComponents.css';
import { useNavigate } from 'react-router-dom';

function HeaderComponent() {
    const navigate = useNavigate();
    return (

        <>
            <div className="header-container">

                <div className="row deneme" >
                    <div className="col-4">
                        <img className="logo" src="/img/logo.png" alt="logo" />
                    </div>
                    <div className="col-8 button-location">
                        <button className="btn1 me-3 mt-1">PRODUCTS</button>
                        <button className="btn1 me-3 mt-1">USER STORIES</button>
                        <button className="signup-btn me-3 mt-4" onClick={() => navigate('/register')}>APPLY NOW</button>
                        <button className="login-btn me-5 mt-4" onClick={() => navigate('/login')}>LOG IN</button>
                    </div>
                </div>
                <div className="slogan">
                    Log in to a More Human Workplace — Humin.
                </div>


                <div className="commercial">
                    <img className="dashboard-img" src="/img/dashboard.png" alt="logo" />
                </div>
            </div>

        </>

    )
}

export default HeaderComponent