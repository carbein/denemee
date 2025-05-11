import 'bootstrap/dist/css/bootstrap.min.css';
import './Product.css';

function Product(){
    return(
        <div className="product-container">
            <div className="product">
                <div className="product-header">
                    <img className="small-image"   src="/img/personel.png"/>
                    <h4 className="product-name-style">
                        Employee Management
                    </h4>
                </div>
                <div className="product-body">
                    Manage all employee information in a single application
                </div>
                <div className="product-btn-bar">
                    <button className="product-manage-btn">
                        Manage
                    </button>
                </div>

            </div>
            <div className="product">
                <div className="product-header">
                    <img className="small-image"   src="/img/expens.png"/>
                    <h4 className="product-name-style">
                        Salary Management
                    </h4>
                </div>
                <div className="product-body">
                    Update, manage, and report salaries
                </div>
                <div className="product-btn-bar">
                    <button className="product-manage-btn">
                        Manage
                    </button>
                </div>
            </div>
            <div className="product">
                <div className="product-header">
                    <img className="small-image"   src="/img/shift.png"/>
                    <h4 className="product-name-style">
                        Shift Management
                    </h4>
                </div>
                <div className="product-body">
                    Plan and manage your working time in the most efficient way
                </div>
                <div className="product-btn-bar">
                    <button className="product-manage-btn">
                        Manage
                    </button>
                </div>
            </div>
            <div className="product">
                <div className="product-header">
                    <img className="small-image"   src="/img/zimmet.png"/>
                    <h4 className="product-name-style">
                        Asset Management
                    </h4>
            </div>
            <div className="product-body">
                Track and manage company assets assigned to your employees
            </div>
                <div className="product-btn-bar">
                    <button className="product-manage-btn">
                        Manage
                    </button>
                </div>
            </div>
        </div>

    )

}
export default Product