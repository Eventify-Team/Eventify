import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBCardText,
  MDBTypography
} from 'mdb-react-ui-kit';

function MyReservations() {
  // Extract the username from the URL parameters
  const { username } = useParams();

  // State to store user's attendances and corresponding events
  const [attendances, setAttendances] = useState([]);
  const [events, setEvents] = useState([]);

  // useEffect runs when component mounts or username changes
  useEffect(() => {
    const fetchUserAttendancesAndEvents = async () => {
      try {
        // Step 1: Get the user object using the username
        const userRes = await fetch(`http://localhost:8080/admin/getUserByUsername/?username=${username}`);
        const userData = await userRes.json();
        const userId = userData.id;

        // Step 2: Get all attendances for the retrieved user ID
        const attRes = await fetch(`http://localhost:8080/user/getAttendancesForUser?userId=${userId}`);
        const attData = await attRes.json();
        setAttendances(attData);

        // Step 3: For each attendance, fetch the corresponding event details
        const eventPromises = attData.map((att) => {
          console.log('Fetching eventId:', att.eventId); // log inside map
          return fetch(`http://localhost:8080/event/getEvent/?eventId=${att.eventId}`).then((res) => res.json());
        });

        const eventResults = await Promise.all(eventPromises); // Wait for all event fetches to complete
        setEvents(eventResults); // Save all fetched events to state
      } catch (error) {
        console.error('Error fetching attendances or events:', error);
      }
    };

    fetchUserAttendancesAndEvents(); // Invoke the async function
  }, [username]);

  return (
    <section className="vh-100" style={{ backgroundColor: '#f4f5f7' }}>
      <MDBContainer className="py-5 h-100">
        <MDBRow className="justify-content-center">
          <MDBCol lg="8">
            <h2 className="mb-4">My Event Attendances</h2>
            {events.length === 0 ? (
              // Show message if there are no events
              <p>No attendances found.</p>
            ) : (
              // Render a card for each event
              events.map((event) => (
                <MDBCard key={event.id || event.eventId} className="mb-3">
                  <MDBCardBody>
                    <MDBTypography tag="h5">{event.name}</MDBTypography>
                    <hr />
                    <MDBCardText><strong>Date:</strong> {event.date} {event.time}</MDBCardText>
                    <MDBCardText><strong>Location:</strong> {event.location}</MDBCardText>
                    <MDBCardText><strong>Description:</strong> {event.description}</MDBCardText>
                    <MDBCardText><strong>Capacity:</strong> {event.capacity}</MDBCardText>
                    <MDBCardText><strong>Duration:</strong> {event.duration}'</MDBCardText>
                    <MDBCardText><strong>Fee:</strong> {event.fee}€</MDBCardText>
                  </MDBCardBody>
                </MDBCard>
              ))
            )}
          </MDBCol>
        </MDBRow>
      </MDBContainer>
    </section>
  );
}

export default MyReservations;