import { useParams } from "react-router-dom";
//import events from "../dataTest/events.js";
import React from 'react';
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import '../App.css';
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBCardText,
  MDBTypography
} from 'mdb-react-ui-kit';
import { useNavigate } from "react-router-dom";
import { Box, Typography, Rating } from '@mui/material';
import 'bootstrap/dist/css/bootstrap.min.css'



function EventDetailsAdmin() {
  //Connection with DB, in order to take the Events
  //Connection with DB, in order to take the number of the reservations for an Event
  const [items, setItems] = useState(null);
  const [reservations, setReservations] = useState(null);
  const [user, setUser] = useState(null);
  const { id } = useParams();
  const username = localStorage.getItem("username");
  const [avgRating, setAvgRating] = useState(null);

  //Used for editing 
  const [editableEvent, setEditableEvent] = useState({});
  const [isEditing, setIsEditing] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
          const fetchData = async () => {
              try {
                //Fetching Event
                  const eventId = parseFloat(id);
                  const response = await fetch(`http://localhost:8080/event/getEvent/?eventId=${eventId}`);
                  const result = await response.json();
                  setItems(result);
                  setEditableEvent(result); // clone event for editing
                //Fetching num of Reservations
                  const response1 = await fetch(`http://localhost:8080/event/getNumberReservationsForEvent/?eventId=${eventId}`);
                  const result1 = await response1.json();
                  setReservations(parseInt(result1));
                //Fetching avg Rating 
                  const rating = await fetch(`http://localhost:8080/event/getAvgRatingForEvent/?eventId=${eventId}`);
                  const avg = await rating.json();
                  setAvgRating(avg);
              } catch (error) {
                  console.error("Error fetching event:", error);
              }
          };
          
          fetchData();
      }, [id]);  

  if (!items) return <p>Event not found</p>; // ή spinner

  

  const update = async () => {
    try {
        await axios.put(`http://localhost:8080/admin/updateEvent`, editableEvent);
        toast.success("Event updated successfully!");
        setIsEditing(false);
        setItems(editableEvent); // refreshes page with new data
    } catch (err) {
        toast.error("Something went wrong during update. Please try again.");
    }
   };

   

   const deleteEvent = async() => {
        try{
            console.log("Attempting to delete event with ID:", id);

            await axios.delete(`http://localhost:8080/admin/deleteEvent`, {params:{eventId:Number(id),}});
            toast.success("Event deleted successfully!");
            setTimeout(() => {
                navigate("/EventsPageAdmin"); 
            }, 1000);

        }catch (err) {
            toast.error("Something went wrong during delete. Please try again.");
        }
   }
    


  return (
    <div className="container py-4">
        
        {/* Image */}
        {items.imageURL && (
        <img
          src={items.imageURL}
          alt={items.name}
          className="img-fluid rounded mb-4"
          style={{ maxHeight: "400px", objectFit: "cover", width: "100%" }}
        />
        )}

        {/* Name */}
        <div className="mb-3 row">
            <label htmlFor="eventName" className="col-sm-2 col-form-label">Name</label>
            <div className="col-sm-10">
                {!isEditing ? (
                <p className="form-control-plaintext">{items.name}</p>
                ) : (
                <input
                    type="text"
                    className="form-control"
                    id="eventName"
                    value={editableEvent.name}
                    onChange={(e) => setEditableEvent({ ...editableEvent, name: e.target.value })}
                />
                )}
            </div>
        </div>

        {/* Date & Time */}
        <div className="mb-3 row">
            <label className="col-sm-2 col-form-label">Date & Time</label>
            <div className="col-sm-5">
            {!isEditing ? (
                <p className="form-control-plaintext">{items.date}</p>
            ) : (
                <input
                type="date"
                className="form-control"
                value={editableEvent.date || ""}
                onChange={(e) => setEditableEvent({ ...editableEvent, date: e.target.value })}
                />
            )}
            </div>
            <div className="col-sm-5">
            {!isEditing ? (
                <p className="form-control-plaintext">{items.time}</p>
            ) : (
                <input
                type="time"
                className="form-control"
                value={editableEvent.time || ""}
                onChange={(e) => setEditableEvent({ ...editableEvent, time: e.target.value })}
                />
            )}
            </div>
        </div>


        {/* Location */}
        <div className="mb-3 row">
            <label htmlFor="eventLocation" className="col-sm-2 col-form-label">Location</label>
            <div className="col-sm-10">
            {!isEditing ? (
                <p className="form-control-plaintext">{items.location}</p>
            ) : (
                <input
                type="text"
                className="form-control"
                id="eventLocation"
                value={editableEvent.location || ""}
                onChange={(e) => setEditableEvent({ ...editableEvent, location: e.target.value })}
                />
            )}
            </div>
        </div>

        {/* Description */}
        <div className="mb-3 row">
            <label htmlFor="eventDescription" className="col-sm-2 col-form-label">Description</label>
            <div className="col-sm-10">
            {!isEditing ? (
                <p className="form-control-plaintext">{items.description}</p>
            ) : (
                <textarea
                className="form-control"
                id="eventDescription"
                rows="3"
                value={editableEvent.description || ""}
                onChange={(e) => setEditableEvent({ ...editableEvent, description: e.target.value })}
                />
            )}
            </div>
        </div>

        {/* Capacity */}
        <div className="mb-3 row">
            <label htmlFor="eventCapacity" className="col-sm-2 col-form-label">Capacity</label>
            <div className="col-sm-10">
            {!isEditing ? (
                <p className="form-control-plaintext">{items.capacity}</p>
            ) : (
                <input
                type="number"
                className="form-control"
                id="eventCapacity"
                value={editableEvent.capacity || ""}
                onChange={(e) => setEditableEvent({ ...editableEvent, capacity: e.target.value })}
                />
            )}
            </div>
        </div>

        {/* Duration */}
        <div className="mb-3 row">
            <label htmlFor="eventDuration" className="col-sm-2 col-form-label">Duration (minutes)</label>
            <div className="col-sm-10">
            {!isEditing ? (
                <p className="form-control-plaintext">{items.duration}'</p>
            ) : (
                <input
                type="number"
                className="form-control"
                id="eventDuration"
                value={editableEvent.duration || ""}
                onChange={(e) => setEditableEvent({ ...editableEvent, duration: e.target.value })}
                />
            )}
            </div>
        </div>

        {/* Fee */}
        <div className="mb-3 row">
            <label htmlFor="eventFee" className="col-sm-2 col-form-label">Fee (€)</label>
            <div className="col-sm-10">
            {!isEditing ? (
                <p className="form-control-plaintext">{items.fee}€</p>
            ) : (
                <input
                type="number"
                className="form-control"
                id="eventFee"
                value={editableEvent.fee || ""}
                onChange={(e) => setEditableEvent({ ...editableEvent, fee: e.target.value })}
                />
            )}
            </div>
        </div>
        
        {/* Average Rating */}
        <div className="mb-4">
            {avgRating !== null && avgRating > 0 ? (
            <Box sx={{ '& > legend': { mt: 2 }, mt: 2, mb: 2 }}>
                <Typography component="legend">Average Rating</Typography>
                <Rating name={`avg-rating-${items.id}`} value={avgRating} precision={0.5} readOnly />
            </Box>
            ) : (
            <Typography>No ratings yet</Typography>
            )}
        </div>

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
        
        {/*Buttons*/}
        <div className="d-flex justify-content-between mt-4">
            {!isEditing ? (
                <>
                <button
                    onClick={() => setIsEditing(true)}
                    className="btn btn-primary"
                >
                    Edit Event
                </button>
                <button
                    onClick={() => {
                    if (window.confirm("Are you sure you want to delete this event?")) {
                        deleteEvent();
                    }
                    }}
                    className="btn btn-outline-danger"
                >
                    Delete Event
                </button>
                </>
            ) : (
                <button
                onClick={update}
                className="btn btn-success ms-auto"
                >
                Save Changes
                </button>
            )}
        </div>

    </div>
  );
}

export default EventDetailsAdmin;