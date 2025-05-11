import 'bootstrap/dist/css/bootstrap.min.css';
import './Body.css';
import Product from "../atoms/Product.tsx";
import UserStories from "../atoms/UserStories.tsx";

function Body(){
    return(
        <div className="body-container">
            <div className="product-container">
                <Product />
            </div>
            <div className={"user-stories-container"}>
                <UserStories />
            </div>
        </div>
    )
}
export default Body