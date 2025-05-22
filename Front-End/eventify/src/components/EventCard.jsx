import { Link } from "react-router-dom";
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function EventCard({ event }) {
  return (
    <Link to={`/event/${event.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
      <div className="card mb-3 shadow" style={{ height: '400px', maxWidth: '18rem', cursor: 'pointer' }}>
        <img src={event.image} alt={event.title} className="card-img-top" style={{ height: '180px', objectFit: 'cover' }} />
        <div className="card-body">
          <p className="card-text text-muted mb-1">{event.date}</p>
          <h5 className="card-title">{event.title}</h5>
          <p className="card-text">{event.location}</p>
        </div>
      </div>
    </Link>
  );
}

export default EventCard;