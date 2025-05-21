import { useState } from "react";
import { Link } from "react-router-dom";


function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false); 

  return (
    <header className="bg-light border-bottom">
      <div className="container-fluid py-2 px-4 d-flex align-items-center justify-content-between">
        {/* Logo */}
        <Link to="/" className="d-flex align-items-center text-decoration-none">
          <img src="/Logo.png" alt="Logo" style={{ height: "40px" }} className="me-2" />
          <span className="fw-bold fs-5 text-dark">Eventify</span>
        </Link>

        {/* Search bar */}
        <form className="d-none d-md-flex flex-grow-1 mx-4" role="search">
          <input
            className="form-control me-2"
            type="search"
            placeholder="Search events..."
            aria-label="Search"
          />
        </form>

        {/* Navigation & User */}
        <div className="d-flex align-items-center gap-3">
          {/* Menu */}
          <nav className="d-none d-lg-flex gap-3">
            <Link to="/" className="text-dark text-decoration-none">Home</Link>
            <Link to="/AboutUs" className="text-dark text-decoration-none">About Us</Link>
            <Link to="/events" className="text-dark text-decoration-none">Events</Link>
            <Link to="/contact" className="text-dark text-decoration-none">Contact Us</Link>
          </nav>

          {/* User dropdown OR Auth buttons */}
          {isLoggedIn ? (
            <div className="dropdown">
              <button
                className="btn btn-light border rounded-circle"
                type="button"
                id="dropdownUser"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <img
                  src="https://via.placeholder.com/30"
                  alt="avatar"
                  className="rounded-circle"
                />
              </button>
              <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownUser">
                <li><Link className="dropdown-item" to="/profile">Profile</Link></li>
                <li><Link className="dropdown-item" to="/reservations">My Reservations</Link></li>
                <li><hr className="dropdown-divider" /></li>
                <li><button className="dropdown-item" onClick={() => setIsLoggedIn(false)}>Logout</button></li>
              </ul>
            </div>
          ) : (
            <div className="d-flex gap-2">
              <Link to="/login" className="btn btn-outline-primary">Login</Link>
              <Link to="/signup" className="btn btn-primary">Sign Up</Link>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}

export default Header;

