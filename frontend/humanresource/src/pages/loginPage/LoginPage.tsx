

import './LoginPage.css'



function LoginPage(){



    return (
<div className="loginPage" >

    <div className="container" >

        <div className="input-area">
            <div className="logol">
                <img src="/img/logo.png" alt="logo"/>
            </div>
            <form className="login-form" >
                <h3 id="h3l">LOGIN</h3>
                <div className="E-mail-group">
                    <label className="Label">E-mail</label>
                    <input type="text" name="Email Area" className="E-mail Input"/>
                </div>
                <div className="Password-group">
                    <label className="Label">Password</label>
                    <input type="password" name="Password Area" className="Password Input" />
                </div>
                <div className="button-login">
                    <button className="login-btn"  >LOG IN</button>
                </div>
                <div className='to-login'>
                    <a id="al" href="/register">create new account</a>
                </div>
            </form>

        </div>
    </div>
</div>
    )

}
export default LoginPage