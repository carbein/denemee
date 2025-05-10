
import { BrowserRouter, Routes, Route } from 'react-router'
import RegisterPage from "./pages/registerPage/RegisterPage.tsx";
import LoginPage from "./pages/loginPage/LoginPage";


function RoutingPage() {
    return (
        <BrowserRouter>
            <Routes>

                <Route path='/login' element={<LoginPage />}/>
                <Route path='/register' element={<RegisterPage />}/>
            </Routes>
        </BrowserRouter>
    )
}

export default RoutingPage