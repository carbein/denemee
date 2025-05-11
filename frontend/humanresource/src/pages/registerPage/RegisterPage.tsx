import './RegisterPage.css'

function RegisterPage(){
    return (
        <div className="RegisterPage" style={{backgroundImage: 'url(img/login-register-background-01.jpg)'}}>
            <div className="container" style={{backgroundImage: 'url(img/login-page-background.png)'}}>
                <div className="input-area">
                    <h3 id="h3">REGISTER</h3>
                    <form className="register-form" >
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
                        <div className="button">
                            <button type='button' >Create Account</button>
                        </div>
                        <div className='to-register'>
                            <a id="a" href="/login">Back to Login</a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    )

}
export default RegisterPage