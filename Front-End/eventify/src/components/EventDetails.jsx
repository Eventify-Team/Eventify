import { useParams } from "react-router-dom";
import events from "../dataTest/events";
import React from 'react';


function EventDetails() {
  const { id } = useParams();
  const event = events.find(e => e.id === parseInt(id));

  if (!event) return <p>Event not found</p>;

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold">{event.title}</h1>
      <img src={event.image} alt={event.title} className="w-full h-64 object-cover my-4" />
      <p><strong>Date:</strong> {event.date}</p>
      <p><strong>Location:</strong> {event.location}</p>
      <p className="my-4"><strong>Περιγραφή:</strong> {event.description}</p>
      <p><strong>Capacity:</strong> {event.capacity}</p>
      <p><strong>Fee:</strong> {event.cost}</p>
      {event.soldOut ? (
        <button className="border border-red-500 text-red-500 px-4 py-2">SOLD OUT</button>
      ) : (
        <button className="border border-green-500 text-green-500 px-4 py-2">BOOKING</button>
      )}
    </div>
  );
}

export default EventDetails;