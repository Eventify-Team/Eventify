import React, { useEffect, useState } from 'react';

const AdminUsers = () => {
  const [users, setUsers] = useState([]);

  // Φόρτωσε χρήστες από backend
  const fetchUsers = async () => {
    try {
      const token = localStorage.getItem("token");
      const res = await fetch("http://localhost:8080/admin/getAllUsers", {
        headers: {
          "Authorization": `Bearer ${token}`
        }
      });
      const data = await res.json();
      setUsers(data);
    } catch (err) {
      console.error("Error fetching users:", err);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  // Διαγραφή χρήστη
  const handleDelete = async (userId) => {
    const confirm = window.confirm("Είσαι σίγουρος ότι θέλεις να διαγράψεις αυτόν τον χρήστη;");
    if (!confirm) return;

    try {
      const token = localStorage.getItem("token");
      const res = await fetch(`http://localhost:8080/admin/deleteUser?userId=${userId}`, {
        method: "DELETE",
        headers: {
          "Authorization": `Bearer ${token}`
        }
      });

      if (res.ok) {
        setUsers(prev => prev.filter(u => u.id !== userId));
      } else {
        alert("Απέτυχε η διαγραφή χρήστη.");
      }
    } catch (err) {
      console.error("Error deleting user:", err);
    }
  };

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
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleDelete(user.id)}
                >
                  Διαγραφή
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminUsers;
