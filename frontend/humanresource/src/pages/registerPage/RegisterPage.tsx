import './RegisterPage.css'

function RegisterPage(){
    return (
        <div className="RegisterPage" >
            <div className="container" >

                <div className="input-area">
                    <div className="logor">
                        <img src="/img/logo.png" alt="logo"/>
                    </div>

                    <form className="register-form" >
                        <h3 id="h3r">REGISTER</h3>
                        <div className="Name-group">
                            <label className="Label" >Name</label>
                            <input type="text" name="Name-Area" className="Name Input"/>
                        </div>
                        <div className="SurName-group">
                            <label className="Label" >SurName</label>
                            <input type="text" name="SurName-Area" className="SurName Input"/>
                        </div>
                        <div className="E-mail-group">
                            <label className="Label">E-mail</label>
                            <input type="text" name="Email Area" className="E-mail Input"/>
                        </div>
                        <div className="Password-group">
                            <label className="Label">Password</label>
                            <input type="password" name="Password Area" className="Password Input" />
                        </div>
                        <div className="Password-group">
                            <label className="Label">Confirm Password</label>
                            <input type="password" name="Password Area" className="Password Input" />
                        </div>
                        <div className="button-register">
                            <button className="signup-btn ">SIGN UP</button>
                        </div>
                        <div className='to-register'>
                            <a id="ar" href="/login">Back to Login</a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    )

}
export default RegisterPage