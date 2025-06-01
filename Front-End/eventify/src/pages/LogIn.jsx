import '../App.css';
import { Link } from 'react-router-dom';
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';


const LogIn = ({ setIsLoggedIn }) => {
  const [users, setUsers] = useState([]);
   const [formData, setFormData] = useState({ 
      username: '',
      password: ''});

    const changeHandler = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
   };
    //Connection with DB, in order to take all the Users 
     useEffect(() => {
             const fetchData = async () => {
                 try {
                     const response = await fetch("http://localhost:8080/admin/getAllUsers");
                     const result = await response.json();
                     setUsers(result);
                 } catch (error) {
                     console.error("Error fetching users:", error);
                 }
             };
             fetchData();
         }, []);
     
      const navigate = useNavigate();

      const submitHandler = async (e) => {
      e.preventDefault();
      const user = users.find(u => u.username === formData.username && u.password === formData.password);

      // if not -> user creation
      if (user) {
        setIsLoggedIn(true);
        localStorage.setItem("username", user.username);
        navigate('/Home');
      }
      else{
        //if exists -> error message
        toast.error('Wrong Username or Password. Please Try Again!');
      }
   };

return(
    <>
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
          {/* format for the message succefully creation or not */}
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
