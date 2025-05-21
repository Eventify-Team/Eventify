import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import EventDetails from "./components/EventDetails";
import EventCard from "./components/EventCard";
import events from "./dataTest/events";
import LogIn from "./pages/LogIn";
import SignUp from "./pages/SignUp";
import AboutUs from "./pages/AboutUs";

function App() {
  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={
            <div className="p-4" 
              style = {{
                display:"grid",
                gridTemplateColumns: "repeat(3, 1fr)",
                gap: "5rem"
                }}
                >
              {events.map(event => (
                <EventCard key={event.id} event={event} />
              ))}
            </div>
          }
        />
        <Route path="/event/:id" element={<EventDetails />} />
        <Route path="/LogIn" element={<LogIn/>}/>
        <Route path="/SignUp" element={<SignUp/>}/>
        <Route path="/AboutUs" element={<AboutUs/>}/>
      </Routes>
    </Router>
  );
}

export default App;