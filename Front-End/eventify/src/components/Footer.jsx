import { useState } from "react";
import { Link } from "react-router-dom";
import '../App.css';

function Footer() {
    return (
        <footer className="bg-primary text-white pt-4 pb-2 mt-5">
            <div className="container">
                <div className="row">
                    <div className="col-md-4 mb-3 d-flex flex-column align-items-start justify-content-start">
                        <div className="d-flex align-items-center mb-2">
                            <img src="logoWhite.png" alt="logo" style={{ height: "150px", marginRight: "10px" }} />
                            
                        </div>
                        <p className="mb-0">Turning moments into memories.</p>
                    </div>
                    <div className="col-md-4 mb-3">
                        <h5>Σύνδεσμοι</h5>
                        <ul className="list-unstyled">
                            <li><a href="/" className="text-white text-decoration-none">Home</a></li>
                            <li><a href="/events" className="text-white text-decoration-none">About Us</a></li>
                            <li><a href="/contact" className="text-white text-decoration-none">Events</a></li>
                            <li><a href="/terms" className="text-white text-decoration-none">Contact Us</a></li>
                        </ul>
                    </div>
                    <div className="col-md-4 mb-3">
                        <h5>Contact</h5>
                        <p>Email: eventify2025uom@gmail.com</p>
                        <p>Phone: +30 2310 1234567</p>
                    </div>
                </div>
                <div className="text-center mt-3 border-top pt-3" style={{ fontSize: '0.875rem' }}>
                    © {new Date().getFullYear()} Eventify. All rights reserved.
                </div>
            </div>
        </footer>

    );
}

export default Footer;