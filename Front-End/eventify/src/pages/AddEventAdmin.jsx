import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { FaCalendarAlt, FaClock, FaMapMarkerAlt, FaUsers, FaEuroSign, FaInfoCircle, FaHeading } from "react-icons/fa";
import { BsCardHeading } from "react-icons/bs";
import { ToastContainer, toast } from 'react-toastify';

const AddEventAdmin = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        description: "",
        duration: "",
        location: "",
        capacity: "",
        date: "",
        time: "",
        fee: "",
        imageURL: "",
    });

    const dataToSend = {
        ...formData,
        duration: parseInt(formData.duration),
        capacity: parseInt(formData.capacity),
        fee: parseFloat(formData.fee),
    };

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
            await axios.post("http://localhost:8080/admin/addEvent", dataToSend);
            toast.success('Event created successfully!');
            //1s delay and after apperars login page
            setTimeout(() => {
                navigate('/EventsPageAdmin'); 
            }, 1000);
        } catch (error) {
            console.error("Error creating event:", error);
            alert("Failed to create event.");
        }
    };

    return (
        <div className="container mt-5 mb-5">
            <h2 className="mb-4 text-center">Create a New Event</h2>

            <form onSubmit={handleSubmit} className="shadow p-4 bg-white rounded">

                <div className="mb-3">
                    <label className="form-label"><BsCardHeading className="me-2" />Title</label>
                    <input type="text" className="form-control" name="name" value={formData.name} onChange={handleChange} required />
                </div>

                <div className="mb-3">
                    <label className="form-label"><FaMapMarkerAlt className="me-2" />Location</label>
                    <input type="text" className="form-control" name="location" value={formData.location} onChange={handleChange} required />
                </div>

                <div className="row mb-3">
                    <div className="col-md-6">
                        <label className="form-label"><FaCalendarAlt className="me-2" />Date</label>
                        <input type="date" className="form-control" name="date" value={formData.date} onChange={handleChange} required />
                    </div>
                    <div className="col-md-6">
                        <label className="form-label"><FaClock className="me-2" />Time</label>
                        <input type="time" className="form-control" name="time" value={formData.time} onChange={handleChange} required />
                    </div>
                </div>

                <div className="row mb-3">
                    <div className="col-md-4">
                        <label className="form-label">⏱ Duration (min)</label>
                        <input type="number" className="form-control" name="duration" value={formData.duration} onChange={handleChange} min="0" />
                    </div>
                    <div className="col-md-4">
                        <label className="form-label"><FaUsers className="me-2" />Capacity</label>
                        <input type="number" className="form-control" name="capacity" value={formData.capacity} onChange={handleChange} min="1" required />
                    </div>
                    <div className="col-md-4">
                        <label className="form-label"><FaEuroSign className="me-2" />Fee (€)</label>
                        <input type="number" className="form-control" name="fee" value={formData.fee} onChange={handleChange} step="0.01" min="0" />
                    </div>
                </div>

                <div className="mb-4">
                    <label className="form-label"><FaInfoCircle className="me-2" />Description</label>
                    <textarea className="form-control" name="description" value={formData.description} onChange={handleChange} rows={4} />
                </div>

                <div className="mb-4">
                    <label className="form-label"><FaInfoCircle className="me-2" />Image URL</label>
                    <input type="text" className="form-control" name="imageURL" value={formData.imageURL} onChange={handleChange} rows={4} />
                </div>

                <div className="text-end">
                    <button type="submit" className="btn btn-primary">Create Event</button>
                </div>
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

export default AddEventAdmin;
