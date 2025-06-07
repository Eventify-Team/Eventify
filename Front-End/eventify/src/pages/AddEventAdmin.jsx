import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


const AddEventAdmin = () => {

    const navigate = useNavigate(); //Useful for redirecting


    const [formData,setFormData] = useState({
        name: "",
        description: "",
        duration:"",
        location: "",
        capacity: "",
        date:"",
        time: "",
        fee: ""
    });

    //Helps with data types compatibility
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
        const response = await axios.post("http://localhost:8080/admin/addEvent", dataToSend);
        alert("Η εκδήλωση δημιουργήθηκε επιτυχώς!");
        navigate("/EventsPageAdmin"); // redirect to admin events
        } catch (error) {
        console.error("Σφάλμα κατά την αποθήκευση:", error);
        alert("Αποτυχία δημιουργίας εκδήλωσης.");
        }
    };

    return(
        <div className="container mt-4">
            <h2>Δημιουργία Εκδήλωσης</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                <label className="form-label">Title</label>
                <input type="text" className="form-control" name="name" value={formData.name} onChange={handleChange} required />
                </div>

                <div className="mb-3">
                <label className="form-label">Location</label>
                <input type="text" className="form-control" name="location" value={formData.location} onChange={handleChange} required />
                </div>

                <div className="row mb-3">
                    <div className="col-md-6">
                        <label className="form-label">Date</label>
                        <input type="date" className="form-control" name="date" value={formData.date} onChange={handleChange} required />
                    </div>
                    <div className="col-md-6">
                        <label className="form-label">Hour</label>
                        <input type="time" className="form-control" name="time" value={formData.time} onChange={handleChange} required />
                    </div>
                </div>

                <div className="mb-3">
                <label className="form-label">Duration (min)</label>
                <input type="number" className="form-control" name="duration" value={formData.duration} onChange={handleChange} />
                </div>

                <div className="mb-3">
                <label className="form-label">Capacity</label>
                <input type="number" className="form-control" name="capacity" value={formData.capacity} onChange={handleChange} required min="1" />
                </div>

                <div className="mb-3">
                <label className="form-label">Fee (€)</label>
                <input type="number" className="form-control" name="fee" value={formData.fee} onChange={handleChange} step="0.01" min="0" />
                </div>

                <div className="mb-3">
                <label className="form-label">Description</label>
                <textarea className="form-control" name="description" value={formData.description} onChange={handleChange} />
                </div>

                <button type="submit" className="btn btn-primary">Create Event</button>
            </form>
        </div>
    );

};

export default AddEventAdmin;
