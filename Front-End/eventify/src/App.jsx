import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header"; 
import EventDetails from "./components/EventDetails";
import EventCard from "./components/EventCard";
import events from "./dataTest/events";
import LogIn from "./pages/LogIn";
import SignUp from "./pages/SignUp";
import AboutUs from "./pages/AboutUs";

function App() {
  return (
    <Router>
      <>
        <Header />
        <Routes>
          <Route
            path="/"
            element={
              <div className="container py-4">
                <div className="row g-5">
                  {events.map((event) => (
                    <div key={event.id} className="col-12 col-md-6 col-lg-4">
                      <EventCard event={event} />
                    </div>
                  ))}
                </div>
              </div>
            }
          />
          <Route path="/event/:id" element={<EventDetails />} />
          <Route path="/LogIn" element={<LogIn />} />
          <Route path="/SignUp" element={<SignUp />} />
          <Route path="/AboutUs" element={<AboutUs/>}/>
        </Routes>
      </>
    </Router>
  );
}

export default App;
