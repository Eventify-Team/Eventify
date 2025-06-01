import { useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { useLocation } from "react-router-dom";



const UpdateUser = () => {

    const location = useLocation();
    const user = location.state?.items;  
    
    const [formData,setFormData] = useState({
        name: String(user.name),
        surname: String(user.surname),
        email: String(user.email),
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
          //Connection with DB in order to submit new user.
          await axios.post(`http://localhost:8080/user/updateUser/?userId=${parseFloat(user.id)}`, formData);
          toast.success('User updated successfully!');
          //2s delay and after apperars login page
          setTimeout(() => {
            navigate('/PersonalProfile'); 
          }, 2000);
        } 
          catch (err) {
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
                <input type="text" className="form-control" name="email" value={formData.email} onChange={handleChange}  />
                </div>

                <button type="submit" className="btn btn-primary">Update User</button>
            </form>
        </div>
    );

};

export default UpdateUser;
