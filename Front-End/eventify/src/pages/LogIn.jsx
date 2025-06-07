import '../App.css';
import { Link, useNavigate } from 'react-router-dom';
import { useState } from "react";
import { ToastContainer, toast } from 'react-toastify';
import transparent_logo from '../images/transparent_logo.png';

const LogIn = ({ setIsLoggedIn }) => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });

  const navigate = useNavigate();

  const changeHandler = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const submitHandler = async (e) => {
  e.preventDefault();

  // Admin login
  try {
    const resAdmin = await fetch("http://localhost:8080/admin/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    });

    if (resAdmin.ok) {
      const data = await resAdmin.json();
      localStorage.setItem("adminUsername", data.username);
      localStorage.setItem("token", data.token);
      setIsLoggedIn(true);
      navigate("/AdminDashboard");
      return;
    }
  } catch (error) {
    console.error("Admin login error:", error);
  }

  // User login
  try {
    const resUser = await fetch("http://localhost:8080/user/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    });

    if (resUser.ok) {
      const data = await resUser.json();
      localStorage.setItem("username", data.username);
      setIsLoggedIn(true);
      navigate("/Home");
      return;
    }
  } catch (error) {
    console.error("User login error:", error);
  }

  toast.error("Wrong Username or Password. Please Try Again!");
};


  return (
    <>
      <div className="login-page d-flex justify-content-center align-items-center">
        <div className="bg-white shadow rounded p-4" style={{ width: '100%', maxWidth: '400px' }}>
          <div className="text-center mb-4">
            <Link to="/Home">
              <img src={transparent_logo} alt="Logo" style={{ height: "100px" }} className="mb-2" />
            </Link>
            <h1 className="fw-bold display-6 mt-2">Log in</h1>
          </div>

          <form onSubmit={submitHandler}>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username:</label>
              <input type="text" name='username' required className="form-control" id="username" value={formData.username} onChange={changeHandler} />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password:</label>
              <input type="password" name='password' required className="form-control" id="password" value={formData.password} onChange={changeHandler} />
            </div>
            <button type="submit" className="btn btn-primary w-100">Log in</button>
            <p className="text-center mt-3">
              Don't have an account? <Link to="/signup">Sign up</Link>
            </p>
          </form>

          <ToastContainer
            position="top-center"
            autoClose={5000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick={false}
            rtl={false}
            pauseOnFocusLoss={false}
            draggable={false}
            pauseOnHover={false}
            theme="light"
          />
        </div>
      </div>
    </>
  );
};

export default LogIn;
