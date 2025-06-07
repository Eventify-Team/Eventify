import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';





const AdminDashboard = () => {

  
const navigate = useNavigate();
useEffect(() => {
  const admin = localStorage.getItem("adminUsername");
  if (!admin) {
    navigate("/login");
  }
}, [navigate]); 


  return (
    <div className="container py-5">
      <h1 className="mb-4">Πίνακας Ελέγχου Διαχειριστή</h1>

      <div className="row g-4">
        {/* Κάρτα Προσθήκης Event */}
        <div className="col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title">Προσθήκη Event</h5>
              <p className="card-text">Δημιούργησε νέο event για να εμφανίζεται στους χρήστες.</p>
              <Link to="/AddEventAdmin" className="btn btn-primary">Μετάβαση</Link>
            </div>
          </div>
        </div>

        {/* Κάρτα Διαχείρισης Χρηστών */}
        <div className="col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title">Διαχείριση Χρηστών</h5>
              <p className="card-text">Δες όλους τους χρήστες, τα emails και τις κρατήσεις τους.</p>
              <Link to="/admin/users" className="btn btn-primary">Μετάβαση</Link>
            </div>
          </div>
        </div>

        {/* Κάρτα Κρατήσεων */}
        <div className="col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title">Κρατήσεις</h5>
              <p className="card-text">Διαχειρίσου τις κρατήσεις που έγιναν από χρήστες.</p>
              <Link to="/admin/reservations" className="btn btn-primary">Μετάβαση</Link>
            </div>
          </div>
        </div>
      </div>

      
    </div>
  );
};

export default AdminDashboard;
