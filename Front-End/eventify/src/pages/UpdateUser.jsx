import { useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';

const UpdateUser = () => {

    const location = useLocation();
    const user = location.state?.items; 
    const username = user.username; 
    const navigate = useNavigate(); // <-- πρέπει να υπάρχει

    
    const [formData,setFormData] = useState({
        name: String(user.name),
        surname: String(user.surname),
        email: String(user.email),
        password: String(user.password)
    });


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
        ...prev,
        [name]: value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
          const userId = parseFloat(user.id);
          //Connection with DB in order to submit new user.
          await axios.put(`http://localhost:8080/user/updateUser`, null, {
                params: {
                  userId: userId,
                  name: formData.name,
                  surname: formData.surname,
                  email: formData.email,
                  password: formData.password
      }
    });    toast.success('User updated successfully!');
          //2s delay and after apperars login page
          setTimeout(() => {
          navigate(`/PersonalProfile/${username}`);
          }, 2000);
        } 
          catch (err) {
            console.log(err);
            toast.error('Something went wrong. Please try again.');
          }
      };
      

    return(
        <div className="container mt-4">
            <h2>Update User</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                <label className="form-label">Name</label>
                <input type="text" className="form-control" name="name" value={formData.name} onChange={handleChange} />
                </div>

                <div className="mb-3">
                <label className="form-label">Surname</label>
                <input type="text" className="form-control" name="surname" value={formData.surname} onChange={handleChange}  />
                </div>

                <div className="mb-3">
                <label className="form-label">Email</label>
                <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange}  />
                </div>

                <div className="mb-3">
                <label className="form-label">Password</label>
                <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange}  />
                </div>

                <button type="submit" className="btn btn-primary">Update User</button>
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
    );

};

export default UpdateUser;
