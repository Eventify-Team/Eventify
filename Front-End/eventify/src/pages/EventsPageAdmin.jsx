import React from 'react';
import { Link } from "react-router-dom";
//import events from "../dataTest/events.js";  
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState, useEffect } from "react";
import EventDetails from './EventDetails';

function EventsPage() {
  //Connection with DB, in order to take the Events 
  const [items, setItems] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/event/getAllEvents");
                const result = await response.json();
                setItems(result);
            } catch (error) {
                console.error("Error fetching events:", error);
            }
        };
        fetchData();
    }, []);  

  return (
    <div className="container my-4">
      <h1 className="mb-4">Events</h1>
      {items.length === 0 ? <p>No events found</p> : null}
      <Link to="/AddEventAdmin">
        <button className="btn btn-primary mb-5">Add Event</button>
      </Link>
      <div className="d-flex flex-wrap gap-3">
        {items.map(event => (
          <EventCard key={event.id} event={event} />
        ))}
      </div>
    </div>
  );
}

function EventCard({ event }) {
  //Connection with DB, in order to take the number of the reservations for an Event
    const [reservations, setReservations] = useState(null);
    useEffect(() => {
          const fetchData = async () => {
              try {
                  const response = await fetch(`http://localhost:8080/event/getNumberReservationsForEvent/?eventId=${event.id}`);
                  const result = await response.json();
                  setReservations(parseInt(result));
              } catch (error) {
                  console.error("Error fetching event:", error);
              }
          };
          fetchData();
      }, []);  

  return (
    <Link to={`/event/${event.id}`} style={{ textDecoration: 'none', color: 'inherit', flex: '0 0 23%', maxWidth: '23%' }}>
      <div className="card mb-3 shadow-sm d-flex flex-column" style={{ cursor: 'pointer', height: '100%' }}>
        {/* <img src="/images/event1.jpg" alt={event.title} className="card-img-top" style={{ height: '180px', objectFit: 'cover' }} />  */}
        <div className="card-body d-flex flex-column justify-content-between" style={{ flexGrow: 1 }}>
          <div>
            <p className="card-text text-muted mb-1">{event.date} {event.time}</p>
            <h5 className="card-title">{event.name}</h5>
            <p className="card-text">{event.location}</p>
          </div>
          {(reservations === event.capacity) && <span className="text-danger mt-auto fw-bold">SOLD OUT</span>}
        </div>
      </div>
    </Link>
  );

}

export default EventsPage;
export { EventCard };