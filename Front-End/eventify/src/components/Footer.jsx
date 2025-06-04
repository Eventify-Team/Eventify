import { Link } from "react-router-dom";
import '../App.css';

function Footer() {
    return (
        <footer className="text-white pt-4 pb-2 mt-5" style={{ backgroundColor: "#34a3da" }} >
            <div className="container">
                <div className="row align-items-start">
                    <div className="col-md-4 mb-3 d-flex flex-column align-items-start justify-content-start">
                        <img
                            src="logoWhiteTrans.png"
                            alt="logo"
                            style={{
                                height: "50px",
                                marginBottom: "20px"
                            }}
                        />
                        <p className="mb-0 mt-2">Turning moments into memories.</p>
                    </div>

                    <div className="col-md-4 mb-3">
                        <h5>Links</h5>
                        <ul className="list-unstyled">
                            <li><Link to="/" className="text-white text-decoration-none">Home</Link></li>
                            <li><Link to="/aboutus" className="text-white text-decoration-none">About Us</Link></li>
                            <li><Link to="/events" className="text-white text-decoration-none">Events</Link></li>
                            <li><Link to="/contact" className="text-white text-decoration-none">Contact Us</Link></li>
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
