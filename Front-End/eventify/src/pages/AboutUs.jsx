import '../App.css';
import { Link } from 'react-router-dom';
import AboutUsHeader from '../Images/AboutUs.png';

const AboutUs = () => {
  return (
    <>
      <div style={{ backgroundColor: '#f8f9fa' }}>
        <div className="header-img">
          <img
            src={AboutUsHeader}
            alt="About Us"
            style={{ width: '100%', height: '100%', objectFit: 'cover' }}
          />
        </div>
        <div className="container py-5">
          <div className="p-4 p-md-5 shadow rounded-4 bg-white">
            <h2 className="fw-bold display-5 mb-3 text-center">About Us</h2>
            <div
              style={{
                height: "4px",
                width: "80px",
                margin: "0 auto 2.5rem auto",
                background: "linear-gradient(to right, #2B5DAE, #34a3da)",
                borderRadius: "5px",
              }}
            ></div>
            <p className="text-start">
              <strong>Eventify</strong> is a full-featured event management platform that allows users to browse upcoming events,
              make reservations, and manage their bookings with ease. The system includes secure user authentication with
              role-based access, enabling regular users to participate in events and administrators to manage event details through
              a dedicated admin panel. Additionally, Eventify offers a dashboard with interactive charts and analytics to help
              organizers monitor engagement and performance. Built using modern technologies such as React, Spring Boot, and Recharts,
              Eventify delivers a seamless and efficient experience for both users and event managers.
            </p>
            <hr className="my-5" />
            <p className="text-start">
              Eventify was developed by a team of five students from the University of Macedonia, Department of Applied Informatics,
              as part of the course <em>"Special Topics in Web Programming"</em>.
            </p>
            <p className="text-start mt-4">
              <strong>The programmers of this project are:</strong> Cenko Florian, Rizoulis Vaios, Samoli Vaia,
              Sousloglou Kyriakos, Stigkas Panteleimon.
            </p>
          </div>
        </div>
      </div>
    </>
  );
};

export default AboutUs;
