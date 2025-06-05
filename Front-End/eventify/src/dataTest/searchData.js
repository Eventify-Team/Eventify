import events from "../dataTest/events";


// Μπορείς να επεκτείνεις αυτό με ό,τι άλλο θες να "φαίνεται" στο search.
const searchData = [
  // Events
  ...events.map(event => ({
    id: event.id,
    title: event.title,
    description: event.description,
    type: "event",
    link: `/event/${event.id}`,
  })),

  // Static pages
  {
    id: "about",
    title: "About Us",
    description: "Learn more about our mission and vision.",
    type: "page",
    link: "/AboutUs",
  },
  {
    id: "contact",
    title: "Contact Us",
    description: "Get in touch with us.",
    type: "page",
    link: "/Contact",
  },

  // Example user profiles (αν έχεις user search)
  {
    id: "user-john",
    title: "John Doe",
    description: "Member of our community",
    type: "user",
    link: "/PersonalProfile/johndoe",
  }
];

export default searchData;
