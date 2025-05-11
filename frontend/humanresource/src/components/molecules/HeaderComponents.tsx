import 'bootstrap/dist/css/bootstrap.min.css';
import './HeaderComponents.css';

function HeaderComponent() {

    return (

        <>
            <div className="header-container">
                <div className="row deneme" >
                    <div className="col-5">
                        <img className="logo" src="/img/logo.png" alt="logo" />
                    </div>
                    <div className="col-1 button-location">
                        <button className="btn1 me-3 mt-4 ">PRODUCTS</button>
                        <button className="btn1 me-3 mt-4 ">USER STORIES</button>
                        <button className="signup-btn me-3 mt-4 ">SIGN UP</button>
                        <button className="login-btn me-5 mt-4" >LOG IN</button>
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