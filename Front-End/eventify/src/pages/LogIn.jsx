import '../App.css';
import { Link } from 'react-router-dom';

const LogIn = () => (
    
        <div className="centered-container">
            <img src="transparent_logo.png" width="300" height="300"></img>
            <h3 className="mb-4">Log In to your account</h3>
            <div className="mb-3" style={{ width: '100%' }}>
                <label className="form-label text-start d-block">Username:</label>
                <input type="text" className="form-control" id="usernameLogIn"/>
            </div>
            <div className="mb-3" style={{ width: '100%' }}>
                <label className="form-label text-start d-block">Password:</label>
                <input type="password" className="form-control" id="passwordLogIn" />
            </div>
            <button className="blueBtn" id="logInBtn" style={{ width: '100%' }}>Log in</button>
            <p className="mt-3">Don't have an account? <Link to="/SignUp">Sign up</Link></p>
        </div>
        
);
export default LogIn;