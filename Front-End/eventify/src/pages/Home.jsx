    import '../App.css';
    import 'bootstrap/dist/css/bootstrap.min.css';
    import event3 from '../Images/event3.jpg';
    import event1 from '../Images/event1.jpg';
    import event2 from '../Images/event2.jpg';
    import 'bootstrap/dist/js/bootstrap.min.js';
    import { EventCard } from '../pages/EventsPage';
    //import events from '../dataTest/events.js';
    import Calendar from '../components/Calendar';
    import { useState, useEffect} from "react";
    import { useLocation, useNavigate } from "react-router-dom";


    const Home = ({ isLoggedIn }) => {

        const navigate = useNavigate();

        //Connection with DB, in order to take the Events
        const [items, setItems] = useState([]);
        const [userEvents, setUserEvents] = useState([]);

        //Validation of token
        useEffect(() => {
            const token = localStorage.getItem("token");
            if (!token) {
            navigate("/login");
            return;
            }

            const validateToken = async () => {
            try {
                const res = await fetch("http://localhost:8080/user/validate", {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`,
                },
                });

                if (!res.ok) {
                // Token είναι άκυρο ή έχει λήξει
                localStorage.clear();
                setIsLoggedIn(false);
                navigate("/login");
                }
            } catch (error) {
                console.error("Token validation error:", error);
                localStorage.clear();
                setIsLoggedIn(false); 
                navigate("/login");
            }
            };

            validateToken();
        }, []);

        //getting all events
        useEffect(() => {
            const fetchData = async () => {
                try {
                    const response = await fetch("http://localhost:8080/event/getAllEvents");
                    const result = await response.json();
                    setItems(result);
                } catch (error) {
                    console.error("Error fetching events:", error);
                }
            };
            fetchData();
        }, []); 



        const location = useLocation();
        const [user, setUser] = useState(null);

        //αποθηκευση στο localstorage για να μη χανεται μετα το refresh
        useEffect(() => {
            const user1 = location.state?.items;
            if (user1) {
                setUser(user1);
                localStorage.setItem("user", JSON.stringify(user1));
            } else {
                const stored = localStorage.getItem("user");
                if (stored) {
                    setUser(JSON.parse(stored));
                }
            }
            const token = localStorage.getItem("token");
            if (!token) {
                // Αν δεν υπάρχει token, κάνε redirect στο login
                navigate("/login");
            }
        }, [location.state]);
            
        const [items1, setItems1] = useState([]);
        useEffect(() => {
            if (!user || !user.id) return; 
            const fetchData = async () => {
                try {
                    const response = await fetch(`http://localhost:8080/user/getAttendancesForUser?userId=${user.id}`);
                    const result = await response.json();
                    setItems1(result);
                } catch (error) {
                    console.error("Error fetching events:", error);
                }
            };
            fetchData();
        }, [user]);


        useEffect(() => {
            if (items.length > 0 && items1.length > 0) {
                    const eventsid = new Set(items1.map(att => att.eventId));
                    const filtered = items.filter(event => eventsid.has(event.id));
                    setUserEvents(filtered);
            }
        }, [items, items1]);

        const upcomingEvents = items
                .filter(event => new Date(event.date) >= new Date()) // keeps only future events
                .sort((a, b) => new Date(a.date) - new Date(b.date)) // sorting
                .slice(0, 3); // keeping the upcoming 3

    
    return (
        <>
                    <div id="carouselExampleAutoplaying" className="carousel slide" data-bs-ride="carousel">
                    <div className="carousel-inner" style={{height:'60vh'}}>
                        <div className="carousel-item active" data-bs-interval="4000">
                            <img src={event1} className="d-block w-100" alt="first-event"/>
                        </div>
                        <div className="carousel-item" data-bs-interval="4000">
                            <img src={event2} className="d-block w-100" alt="second-event"/>
                        </div>
                        <div className="carousel-item" data-bs-interval="4000">
                            <img src={event3} className="d-block w-100" alt="third-event"/>
                        </div>
                    </div>
                    <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Previous</span>
                    </button>
                    <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                        <span className="visually-hidden">Next</span>
                    </button>
                    </div>
                    <div className="container main-cont py-4">
                    <h1 style={{alignSelf: 'center', marginTop: '50px', justifySelf: 'center'}} className="animate">
                        Welcome to <span>E</span><span>V</span><span>E</span><span>N</span><span>T</span><span>I</span><span>F</span><span>Y</span>
                    </h1>
                    <p style={{alignSelf: 'center', marginBottom: '50px', fontStyle: 'oblique', justifySelf: 'center'}}>
                        Life is made of moments. Make yours count.
                    </p>
                    <h2 style={{marginBottom: '50px'}}>
                        Upcoming events...
                    </h2>
                    <div className="container py-4">
                        <div className="row g-2">
                            {upcomingEvents.map((event) => (
                                <div key={event.id} className="col-12 col-md-6 col-lg-4">
                                    <EventCard event={event} />
                                </div>
                            ))}
                            <a href="http://localhost:5173/events" style={{display:'inline-block'}}>see more...</a>
                        </div> 
                    </div>
                    {isLoggedIn &&(
                        <>
                            <h2 style={{marginTop: '50px'}}>
                                My Calendar
                            </h2>
                            <div style={{}}>
                                <Calendar eventDates={userEvents.map(e => e.date)} />
                               
                            </div>
                        
                        </>
                        
                    )}
            
                    </div >
                    
        </>
        
    );
    }

    export default Home;


    console.log("Home loaded");