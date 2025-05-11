import 'bootstrap/dist/css/bootstrap.min.css';
import './HeaderComponents.css';
import { useNavigate } from 'react-router-dom';

function HeaderComponent() {
    const navigate = useNavigate();
    return (

        <>
            <div className="header-container">
                <div className="row deneme" >
                    <div className="col-5">
                        <img className="logo" src="/img/logo.png" alt="logo" />
                    </div>
                    <div className="col-1 button-location">
                        <button className="buttonheader1 ">PRODUCTS</button>
                        <button className="buttonheader1 ">USER STORIES</button>
                        <button className="signupbuttonheader" onClick={() => navigate('/register')}>SIGN UP</button>
                        <button className="loginbuttonheader" onClick={() => navigate('/login')}>LOG IN</button>
                    </div>
                </div>
                <div className="slogan">
                    Log in to a More Human Workplace — Humin.
                </div>


                <div className="commercial">
                    <img className="dashboard-img" src="/img/dasbord_example.png" alt="logo" />
                </div>
            </div>

        </>

    )
}

export default HeaderComponent