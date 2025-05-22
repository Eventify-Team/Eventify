import React from 'react';
import { Link } from "react-router-dom";
import events from "../dataTest/events.js";  
import 'bootstrap/dist/css/bootstrap.min.css';


function EventCard({ event }) {
  return (
    <Link to={`/event/${event.id}`} style={{ textDecoration: 'none', color: 'inherit', flex: '0 0 23%', maxWidth: '23%' }}>
      <div className="card mb-3 shadow-sm d-flex flex-column" style={{ cursor: 'pointer', height: '100%' }}>
        <img src={event.image} alt={event.title} className="card-img-top" style={{ height: '180px', objectFit: 'cover' }} />
        <div className="card-body d-flex flex-column justify-content-between" style={{ flexGrow: 1 }}>
          <div>
            <p className="card-text text-muted mb-1">{event.date}</p>
            <h5 className="card-title">{event.title}</h5>
            <p className="card-text">{event.location}</p>
          </div>
          {event.soldOut && <span className="text-danger mt-auto fw-bold">SOLD OUT</span>}
        </div>
      </div>
    </Link>
  );
}

function EventsPage() {
  return (
    <div className="container my-4">
      <h1 className="mb-4">Events</h1>
      <div className="d-flex flex-wrap gap-3">
        {events.map(event => (
          <EventCard key={event.id} event={event} />
        ))}
      </div>
    </div>
  );
}

export default EventsPage;
export { EventCard };