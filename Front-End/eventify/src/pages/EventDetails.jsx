import { useParams } from "react-router-dom";
//import events from "../dataTest/events.js";
import React from 'react';
import { useState, useEffect } from "react";


function EventDetails() {
  //Connection with DB, in order to take the Events
  //Connection with DB, in order to take the number of the reservations for an Event
  const [items, setItems] = useState(null);
  const [reservations, setReservations] = useState(null);
  const { id } = useParams();
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
    


  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold">{items.name}</h1>
      {/* <img src={event1} alt={items.name} className="w-full h-64 object-cover my-4" /> */}
      <p><strong>Date:</strong> {items.date} {items.time}</p>
      <p><strong>Location:</strong> {items.location}</p>
      <p className="my-4"><strong>Description:</strong> {items.description}</p>
      <p><strong>Capacity:</strong> {items.capacity}</p>
      <p><strong>Duration:</strong> {items.duration}'</p>
      <p><strong>Fee:</strong> {items.fee}€</p>
      
      {isSoldOut(items.capacity, reservations) ? (
        <button className="border border-red-500 text-red-500 px-4 py-2">SOLD OUT</button>
      ) : (
        <button className="border border-green-500 text-green-500 px-4 py-2">BOOKING</button>
      )} 
    </div>
  );
}

export default EventDetails;