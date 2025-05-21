import '../App.css';
import { Link } from 'react-router-dom';

const AboutUs = () => {
    return(<>
        <div className="AboutUs">
            <img src='about_us.png' style={{width:'100%',  height: '250px', objectFit: 'cover', display: 'block'}}/>
            <h2 style={{fontWeight:'bold', fontSize:"60px"}}>About Us</h2>
            <p style={{textAlign:'left'}}>Eventify is a full-featured event management platform that allows users to browse upcoming events, make reservations, and manage their bookings with ease. 
                The system includes secure user authentication with role-based access, enabling regular users to participate in events and administrators to manage event details through a dedicated admin panel. 
                Additionally, Eventify offers a dashboard with interactive charts and analytics to help organizers monitor engagement and performance. Built using modern technologies such as React, Spring Boot, and Recharts, Eventify delivers a seamless and efficient experience for both users and event managers.
                Eventify was developed by a team of five students from the University of Macedonia, Department of Applied Informatics, as part of the course "Special Topics in Web Programming". <br></br>
                The programmers of this project are: Cenko Florian, Rizoulis Vaios, Samoli Vaia, Sousloglou Kyriakos, Stigkas Panteleimon.</p>
        </div>
    </>)
};
export default AboutUs;