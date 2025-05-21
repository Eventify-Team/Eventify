import { useState } from "react";
import { Link } from "react-router-dom";

function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <header className="bg-white border-bottom sticky-top shadow-sm z-3 w-100">
      <div className="d-flex justify-content-center">
        <div className="w-100 px-4" style={{ maxWidth: "1440px" }}>
          <div className="d-flex align-items-center justify-content-between py-2">

             {/* Logo */}
        <Link to="/" className="d-flex align-items-center text-decoration-none">
          <img src="/transparent_logo.png" alt="Logo" className="me-2" style={{ height: "60px", maxHeight: "10vh" }} />
          
        </Link>

            {/* Search bar */}
            <form className="d-none d-md-flex flex-grow-1 mx-4" role="search">
              <input
                className="form-control me-2 rounded-pill px-3"
                type="search"
                placeholder="Search events..."
                aria-label="Search"
              />
            </form>

            {/* Navigation & User */}
            <div className="d-flex align-items-center gap-3">
              <nav className="d-none d-lg-flex gap-3">
                <Link to="/Home" className="text-dark text-decoration-none nav-link">Home</Link>
                <Link to="/AboutUs" className="text-dark text-decoration-none nav-link">About Us</Link>
                <Link to="/events" className="text-dark text-decoration-none nav-link">Events</Link>
                <Link to="/contact" className="text-dark text-decoration-none nav-link">Contact Us</Link>
              </nav>

              {isLoggedIn ? (
                <div className="dropdown">
                  <button
                    className="btn btn-light border rounded-circle p-0"
                    type="button"
                    id="dropdownUser"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <img
                      src="https://via.placeholder.com/36"
                      alt="avatar"
                      className="rounded-circle"
                      style={{ width: "36px", height: "36px" }}
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
                  <Link to="/login" className="btn btn-outline-primary rounded-pill px-3">Login</Link>
                  <Link to="/signup" className="btn btn-primary rounded-pill px-3">Sign Up</Link>
                </div>
              )}
            </div>
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
