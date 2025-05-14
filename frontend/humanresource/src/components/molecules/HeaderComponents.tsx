import 'bootstrap/dist/css/bootstrap.min.css';
import './HeaderComponents.css';
import { useNavigate } from 'react-router-dom';
import { useRef, useState, useEffect } from 'react';

function HeaderComponent() {
    const navigate = useNavigate();
    const [menuOpen, setMenuOpen] = useState(false);
    const dropdownRef = useRef<HTMLDivElement>(null);

    // Dışarı tıklanınca kapat
    useEffect(() => {
        function handleClickOutside(event: MouseEvent) {
            const target = event.target as Node | null;

            if (!target) return;
            if (!dropdownRef.current) return;

            if (!dropdownRef.current.contains(target)) {
                setMenuOpen(false);
            }
        }

        document.addEventListener("mousedown", handleClickOutside);
        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);


    return (
        <>
            <div className="header-container">
                <div className="row deneme">
                    <div className="col-4">
                        <img className="logo" src="/img/logo1.png" alt="logo" />
                    </div>
                    <div className="col-8 button-location position-relative">
                        <button
                            className="btn1 me-3 mt-1 position-relative"
                            onClick={() => setMenuOpen(!menuOpen)}
                        >
                            PRODUCTS
                        </button>

                        {/* DROPDOWN */}
                        {menuOpen && (
                            <div className="products-dropdown" ref={dropdownRef}>
                                <a href="#" className="dropdown-item">Employee Management</a>
                                <a href="#" className="dropdown-item">Salary Management</a>
                                <a href="#" className="dropdown-item">Shift Management</a>
                                <a href="#" className="dropdown-item">Asset Management</a>
                            </div>
                        )}

                        <button className="btn1 me-3 mt-1">USER STORIES</button>
                        <button className="signup-btn me-3 mt-4" onClick={() => navigate('/register')}>APPLY NOW</button>
                        <button className="login-btn me-5 mt-4" onClick={() => navigate('/login')}>LOG IN</button>
                    </div>
                </div>

                {/* Blur sadece bu kısmı etkilesin */}
                <div className={`blur-zone ${menuOpen ? 'blurred' : ''}`}>
                    <div className="slogan">
                        Log in to a More Human Workplace — Humin.
                    </div>

                    <div className="commercial">
                        <img className="dashboard-img" src="/img/dashboard.png" alt="logo" />
                    </div>
                </div>
            </div>
        </>
    );
}

export default HeaderComponent;
