import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminReservations = () => {
  const [reservations, setReservations] = useState([]);
  const [sortField, setSortField] = useState('id');
  const [sortOrder, setSortOrder] = useState('asc');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [attRes, userRes, eventRes] = await Promise.all([
          axios.get("http://localhost:8080/admin/getAllAttendances"),
          axios.get("http://localhost:8080/admin/getAllUsers"),
          axios.get("http://localhost:8080/admin/getAllEvents")
        ]);

        const attendances = attRes.data;
        const users = userRes.data;
        const events = eventRes.data;

        // Maps for easier search
        const userMap = new Map(users.map(user => [user.id, user.username]));
        const eventMap = new Map(events.map(event => [event.id, { name: event.name, date: event.date }]));

        // Combining data
        const enrichedReservations = attendances.map(att => ({
          id: att.id,
          userName: userMap.get(att.userId) || "Unknown User",
          eventName: eventMap.get(att.eventId)?.name || "Unknown Event",
          eventDate: eventMap.get(att.eventId)?.date || "Unknown Date"
        }));

        setReservations(enrichedReservations);
      } catch (error) {
        console.error("Failed to fetch data:", error);
      }
    };

    fetchData();
  }, []);

  const handleSort = (field) => {
    const order = sortField === field && sortOrder === 'asc' ? 'desc' : 'asc';
    setSortField(field);
    setSortOrder(order);
  };

  const sortedReservations = [...reservations].sort((a, b) => {
    const aField = a[sortField] ?? '';
    const bField = b[sortField] ?? '';
    if (typeof aField === 'string') {
      return sortOrder === 'asc' ? aField.localeCompare(bField) : bField.localeCompare(aField);
    }
    return sortOrder === 'asc' ? aField - bField : bField - aField;
  });

  return (
    <div className="container py-5">
      <h2 className="mb-4">All Reservations</h2>
      <div className="table-responsive">
        <table className="table table-bordered table-hover align-middle">
          <thead className="table-light">
            <tr>
              <th onClick={() => handleSort("id")} style={{ cursor: "pointer" }}>Reservation ID</th>
              <th onClick={() => handleSort("eventDate")} style={{ cursor: "pointer" }}>Date</th>
              <th onClick={() => handleSort("userName")} style={{ cursor: "pointer" }}>User</th>
              <th onClick={() => handleSort("eventName")} style={{ cursor: "pointer" }}>Event Name</th>
            </tr>
          </thead>
          <tbody>
            {sortedReservations.map(res => (
              <tr key={res.id}>
                <td>{res.id}</td>
                <td>{res.eventDate}</td>
                <td>{res.userName}</td>
                <td>{res.eventName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminReservations;