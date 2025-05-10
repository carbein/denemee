

import './LoginPage.css'


function LoginPage(){



    return (
<div className="loginPage">
    <div className="container" style={{backgroundImage: 'url(img/login-page-background.png)'}}>
        <div className="input-area">
            <form id="login-form" >
                <div className="E-mail-group">
                    <label className="Label">E-mail</label>
                    <input type="text" name="Email Area" className="E-mail Input"/>
                </div>
                <div className="Password-group">
                    <label className="Label">Password</label>
                    <input type="password" name="Password Area" className="Password Input" />
                </div>
                <div className="button">
                <button type='button' >Login</button>
                </div>
            </form>

        </div>
    </div>
</div>
    )

}
export default LoginPage