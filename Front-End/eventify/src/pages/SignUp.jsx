import '../App.css';
import { Link } from 'react-router-dom';

const SignUp = () => (
    <div className="centered-container">
            <img src="transparent_logo.png" width="300" height="300"></img>
            <h3 className="mb-4">Create your account</h3>
            <form>
                <div className="row mb-3">
                    <div className="col text-start">
                        <input type="text" id="firstNameInput"className="form-control" placeholder="First name" />
                    </div>
                    <div className="col text-start">
                        <input type="text" id="lastNameInput"className="form-control" placeholder="Last name" />
                    </div>
                </div>
                <div className="row mb-3">
                        <input type="text" id="usernameInput" className="form-control" placeholder="Username" />
                </div>
                <div className="row mb-3">
                        <input type="password" id="passwordInput" className="form-control" placeholder="Password" />
                </div>
                <div className="row mb-3">
                        <input type="text" id="emailInput"className="form-control" placeholder="Email" />
                </div>
            </form>
            <button className="blueBtn" id="SignInBtn" style={{ width: '100%' }}>Sign in</button>
            <p className="mt-3">Already got an account? <Link to="/LogIn">Log In</Link></p>
        </div>

);
export default SignUp;