import EventCard from "../components/EventCard";
import events from "../dataTest/events";

function Home() {
  return (
    <div className="container py-4">
      <div className="row g-5">
        {events.map((event) => (
          <div key={event.id} className="col-12 col-md-6 col-lg-4">
            <EventCard event={event} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;


console.log("Home loaded");