import '../App.css';
import { Link } from 'react-router-dom';

const LogIn = () => (
  <div className="login-page d-flex justify-content-center align-items-center">
    <div className="bg-white shadow rounded p-4" style={{ width: '100%', maxWidth: '400px' }}>
      <div className="text-center mb-4">
        {/* Κάνουμε το logo link προς Home */}
        <Link to="/Home">
          <img
            src="/transparent_logo.png"
            alt="Logo"
            style={{ height: "100px" }}
            className="mb-2"
          />
        </Link>
        <h1 className="fw-bold display-6 mt-2">Log in</h1>
      </div>

      <form>
        <div className="mb-3">
          <label htmlFor="username" className="form-label">Username:</label>
          <input type="text" className="form-control" id="username" />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">Password:</label>
          <input type="password" className="form-control" id="password" />
        </div>
        <button type="submit" className="btn btn-primary w-100">Log in</button>
        <p className="text-center mt-3">
          Don't have an account? <Link to="/signup">Sign up</Link>
        </p>
      </form>
    </div>
  </div>
);

export default LogIn;
