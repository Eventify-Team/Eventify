import React, { useEffect, useState } from 'react';

const AdminUsers = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    //  Φέρε χρήστες από backend υπο καταστκευη , δοκιμαστικα παραδειγματα
    setUsers([
      { id: 1, username: 'john_doe', email: 'john@example.com' },
      { id: 2, username: 'maria92', email: 'maria@example.com' },
    ]);
  }, []);

  return (
    <div className="container py-5">
      <h2 className="mb-4">Χρήστες</h2>
      <table className="table table-bordered">
        <thead className="table-light">
          <tr>
            <th>ID</th>
            <th>Όνομα χρήστη</th>
            <th>Email</th>
            <th>Ενέργειες</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td>
                <button className="btn btn-danger btn-sm">Διαγραφή</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminUsers;
