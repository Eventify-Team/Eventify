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

import { Box, Typography, Rating } from '@mui/material';



function EventDetails() {
  //Connection with DB, in order to take the Events
  //Connection with DB, in order to take the number of the reservations for an Event
  const [items, setItems] = useState(null);
  const [reservations, setReservations] = useState(null);
  const [user, setUser] = useState(null);
  const { id } = useParams();
  const username = localStorage.getItem("username");
  const [avgRating, setAvgRating] = useState(null);
  useEffect(() => {
          const fetchData = async () => {
              try {
                  const eventId = parseFloat(id);
                  const response = await fetch(`http://localhost:8080/event/getEvent/?eventId=${eventId}`);
                  const result = await response.json();
                  setItems(result);
                  const response1 = await fetch(`http://localhost:8080/event/getNumberReservationsForEvent/?eventId=${eventId}`);
                  const result1 = await response1.json();
                  setReservations(parseInt(result1));
                  const usernameExists = String(username);
                  const response2 = await fetch(`http://localhost:8080/admin/getUserByUsername/?username=${usernameExists}`);
                  const result2 = await response2.json();
                  setUser(result2);
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

  function isSoldOut(capacityEvent, reservation){
    if(reservation == capacityEvent)
      return true;
    else
      return false;
  }

  const booking = async () => {
    try {
      await axios.post(`http://localhost:8080/user/addAttendance?userId=${user.id}&eventId=${items.id}`);
      toast.success("Reservation completed!");
      setTimeout(() => {
            navigate('/EventsPage'); 
          }, 2000);
    } catch (err) {
      toast.error("Something went wrong during registration. Please try again.");
    }
  };
    


  return (
    <div className="p-4">
       {items.imageURL && (<img src={items.imageURL} alt={items.name} className="w-full h-64 object-cover my-4" style={{ maxHeight: '400px', width: '100%', objectFit: 'cover', borderRadius: '8px' }}/>)}
      <h1 className="text-2xl font-bold">{items.name}</h1>
      {/* <img src={event1} alt={items.name} className="w-full h-64 object-cover my-4" /> */}
      <p><strong>Date:</strong> {items.date} {items.time}</p>
      <p><strong>Location:</strong> {items.location}</p>
      <p className="my-4"><strong>Description:</strong> {items.description}</p>
      <p><strong>Capacity:</strong> {items.capacity}</p>
      <p><strong>Duration:</strong> {items.duration}'</p>
      <p><strong>Fee:</strong> {items.fee}€</p>
      {/*Field for average rating of event
      {avgRating > 0 ? (
        <p><strong>Average rating:</strong> {avgRating}/5</p>
      ) : (
        <p><em>No ratings yet.</em></p>
      )}*/}
    
      {avgRating !== null && avgRating > 0  ? (
        <Box sx={{ '& > legend': { mt: 2 }, mt: 2, mb:2 }}>
          <Typography component="legend">Average Rating</Typography>
          <Rating
            name={`avg-rating-${items.id}`}
            value={avgRating}
            precision={0.5}
            readOnly
          />
        </Box>
      ) : (
        <Typography>No ratings yet</Typography>
      )}

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
      
      {isSoldOut(items.capacity, reservations) ? (
        <button className="border border-red-500 text-red-500 px-4 py-2">SOLD OUT</button>
      ) : (
        <button onClick={booking} className="border border-green-500 text-green-500 px-4 py-2">BOOKING</button>
      )} 
    </div>
  );
}

export default EventDetails;