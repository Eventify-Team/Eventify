import { BrowserRouter as Router, Routes, Route, Navigate, useLocation} from "react-router-dom";
import { useState} from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import AboutUs from "./pages/AboutUs";
import LogIn from "./pages/LogIn";
import SignUp from "./pages/SignUp";
import NotFound404 from "./pages/NotFound404";
import EventsPage from "./pages/EventsPage";
import EventDetails from "./pages/EventDetails";
import AddEventAdmin from "./pages/AddEventAdmin";
import EventsPageAdmin from "./pages/EventsPageAdmin";
import PersonalProfile from "./pages/PersonalProfile";
import UpdateUser from "./pages/UpdateUser";




function AppContent() {
  const location = useLocation();
  const hideHeaderPaths = ["/login", "/signup"];
  const shouldHideHeader = hideHeaderPaths.includes(location.pathname.toLowerCase());
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  return (
    <div className="page-wrapper d-flex flex-column min-vh-100"> {/* fit to screen!!!!! */}
     {/* {!shouldHideHeader && <Header />} */}
      {!shouldHideHeader && <Header isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />} 
      <Routes>
        <Route path="/" element={<Navigate to="/Home" />} />
        <Route path="/Home" element={<Home isLoggedIn={isLoggedIn}/>} />
        <Route path="/AboutUs" element={<AboutUs />} />
        <Route path="/LogIn" element={<LogIn setIsLoggedIn={setIsLoggedIn} />} />
        <Route path="/SignUp" element={<SignUp />} />
        <Route path="/events" element={<EventsPage />} />
        <Route path="/event/:id" element={<EventDetails />} />  {/* Λεπτομέρειες event */}
        <Route path="*" element={<NotFound404 />} />
        <Route path="/AddEventAdmin" element={<AddEventAdmin/>} />
        <Route path="/EventsPageAdmin" element = {<EventsPageAdmin/>} />
        <Route path="/PersonalProfile/:username" element={<PersonalProfile/>} />
        <Route path="/UpdateUser" element = {<UpdateUser />} />
      </Routes>
       {!shouldHideHeader && <Footer />}
    </div>
  );
}

function App() {
  return (
    <Router>
      <AppContent />
    </Router>
  );
}

export default App;
