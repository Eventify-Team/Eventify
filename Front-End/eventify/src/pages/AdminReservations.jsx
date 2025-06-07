import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminReservations = () => {
  const [reservations, setReservations] = useState([]);
  const [sortField, setSortField] = useState('id');
  const [sortOrder, setSortOrder] = useState('asc');

  useEffect(() => {
    const fetchReservations = async () => {
      try {
        const response = await axios.get("http://localhost:8080/admin/getAllReservations");

        setReservations(response.data);
      } catch (error) {
        console.error("Failed to fetch reservations:", error);
      }
    };

    fetchReservations();
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
              <th onClick={() => handleSort("user.username")} style={{ cursor: "pointer" }}>User</th>
              <th onClick={() => handleSort("id")} style={{ cursor: "pointer" }}>Reservation ID</th>
              <th onClick={() => handleSort("date")} style={{ cursor: "pointer" }}>Date</th>
              <th onClick={() => handleSort("event.name")} style={{ cursor: "pointer" }}>Event Name</th>
            </tr>
          </thead>
          <tbody>
            {sortedReservations.map(res => (
              <tr key={res.id}>
                <td>{res.user?.username ?? 'N/A'}</td>
                <td>{res.id}</td>
                <td>{res.date}</td>
                <td>{res.event?.name ?? 'N/A'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminReservations;
