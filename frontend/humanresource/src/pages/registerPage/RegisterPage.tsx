import './RegisterPage.css'

function RegisterPage(){
    return (
        <div className="loginPage" style={{backgroundImage: 'url(img/login-register-background-01.jpg)'}}>
            <div className="container" style={{backgroundImage: 'url(img/login-page-background.png)'}}>
                <div className="input-area">
                    <form id="login-form" >
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
                        <div className='to-login'>
                            <a href="/login">Back to Login</a>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    )

}
export default RegisterPage