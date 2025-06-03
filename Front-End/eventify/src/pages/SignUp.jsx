import '../App.css';
import { Link } from 'react-router-dom';
import { useState, useEffect } from "react";
import React from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import transparent_logo from '../images/transparent_logo.png';


const SignUp = () => {
  const [formData, setFormData] = useState({ 
    name: '',
    surname: '',
    username: '',
    password: '', 
    email: '' });
  const [users, setUsers] = useState([]);
  
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

      //Check if the username already exists in the DB
      const usernameExists = users.some(user => user.username === formData.username);
      
      // if not -> user creation
      if (!usernameExists) {
        try {
          //Connection with DB in order to submit new user.
          await axios.post('http://localhost:8080/user/addUser', formData);
          toast.success('User created successfully!');
          //2s delay and after apperars login page
          setTimeout(() => {
            navigate('/login'); 
          }, 2000);
        } 
          catch (err) {
            toast.error('Something went wrong during registration. Please try again.');
          }
      }
      else{
        //if exists -> error message
        toast.error('Username already exists. Please choose another one.');
      }
   };

  return(
    <>
      <div className="login-page d-flex justify-content-center align-items-center">
        <div className="bg-white shadow rounded p-4" style={{ width: '100%', maxWidth: '400px' }}>
          <div className="text-center mb-4">
            <Link to="/Home">
              <img src={transparent_logo} alt="Logo" style={{ height: "100px" }} className="mb-2" />
            </Link>
            <h1 className="fw-bold display-6 mt-2">Create your account</h1>
          </div>

          <form onSubmit={submitHandler}>
            <div className="row mb-3">
              <div className="col">
                <input type="text" required name="name" id="firstNameInput" className="form-control" placeholder="First name" value={formData.name} onChange={changeHandler} />
              </div>
              <div className="col">
                <input type="text" required name="surname" id="lastNameInput" className="form-control" placeholder="Last name" value={formData.surname} onChange={changeHandler}/>
              </div>
            </div>
            <div className="mb-3">
              <input type="text" required name="username" id="usernameInput" className="form-control" placeholder="Username" value={formData.username} onChange={changeHandler}/>
            </div>
            <div className="mb-3">
              <input type="password" required name="password" id="passwordInput" className="form-control" placeholder="Password" value={formData.password} onChange={changeHandler}/>
            </div>
            <div className="mb-3">
              <input type="email" required name="email" id="emailInput" className="form-control" placeholder="Email" value={formData.email} onChange={changeHandler}/>
            </div>
            <button type="submit" className="btn btn-primary w-100">Sign up</button>
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

          <p className="text-center mt-3">
            Already have an account? <Link to="/login">Log in</Link>
          </p>
        </div>
      </div>
      
  </>
  
  );
  
}

export default SignUp;
