import '../App.css';
import { Link } from 'react-router-dom';

const SignUp = () => (
  <div className="login-page d-flex justify-content-center align-items-center">
    <div className="bg-white shadow rounded p-4" style={{ width: '100%', maxWidth: '400px' }}>
      <div className="text-center mb-4">
        <Link to="/Home">
          <img src="/transparent_logo.png" alt="Logo" style={{ height: "100px" }} className="mb-2" />
        </Link>
        <h1 className="fw-bold display-6 mt-2">Create your account</h1>
      </div>

      <form>
        <div className="row mb-3">
          <div className="col">
            <input type="text" id="firstNameInput" className="form-control" placeholder="First name" />
          </div>
          <div className="col">
            <input type="text" id="lastNameInput" className="form-control" placeholder="Last name" />
          </div>
        </div>
        <div className="mb-3">
          <input type="text" id="usernameInput" className="form-control" placeholder="Username" />
        </div>
        <div className="mb-3">
          <input type="password" id="passwordInput" className="form-control" placeholder="Password" />
        </div>
        <div className="mb-3">
          <input type="email" id="emailInput" className="form-control" placeholder="Email" />
        </div>
        <button type="submit" className="btn btn-primary w-100">Sign up</button>
      </form>

      <p className="text-center mt-3">
        Already have an account? <Link to="/login">Log in</Link>
      </p>
    </div>
  </div>
);

export default SignUp;
