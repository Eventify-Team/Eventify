import {useState} from "react";
import "../App.css"; 

const Calendar = () => {



 
  const months = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];

  const [date, setDate] = useState(new Date());

  const currYear = date.getFullYear();
  const currMonth = date.getMonth();
  const currDay = date.getDate();

  const firstDayOfMonth = new Date(currYear, currMonth, 1).getDay();
  const lastDateOfMonth = new Date(currYear, currMonth + 1, 0).getDate();
  const lastDayOfMonth = new Date(currYear, currMonth, lastDateOfMonth).getDay();
  const lastDateOfLastMonth = new Date(currYear, currMonth, 0).getDate();

  const [events, setEvents] = useState([{
    date: new Date(currYear,currMonth,currDay+1) 
  }
]);

  // setEvents(prev => [...prev, { 
  //  date: ""
  // }
  // ]);

  const generateCalendarDays = () => {
    
    let days = [
    ];

    let flag = true;

    for (let i = firstDayOfMonth === 0 ? 6 : firstDayOfMonth - 1; i > 0; i--) {
      days.push(<li className="inactive">{lastDateOfLastMonth - i + 1}</li>);
    }

    for (let i = 1; i <= lastDateOfMonth; i++) {

      days.push(
        <li className={(events.some(event =>
          event.date.getFullYear() === currYear &&
          event.date.getMonth() === currMonth &&
          event.date.getDate() === i)) ? "active" : ""}>
          {i}
        </li>
      );

    }
    
    let k = lastDayOfMonth === 0 ? 0 : 7 - lastDayOfMonth;
    for (let i = 1; i <= k; i++) {
      days.push(<li className="inactive">{i}</li>);
    }

        
    return days;
  };

  const handlePrev = () => {
    setDate(prev => new Date(prev.getFullYear(), prev.getMonth() - 1, 1));
  };

  const handleNext = () => {
    setDate(prev => new Date(prev.getFullYear(), prev.getMonth() + 1, 1));
  };

  return (
    <div className="calendar-container">
      <div className="header">
        <span className="prevnext" onClick={handlePrev} id="prev">&#8249;</span>
        <span className="this-month">{months[currMonth]} {currYear}</span>
        <span className="prevnext" onClick={handleNext} id="next">&#8250;</span>
      </div>
      <div style={{backgroundColor: '#edf5f6'}}>
        <ul className="daysList" style={{marginBottom: '0', paddingLeft: '0'}}>
            <li>Mon</li>
            <li>Tue</li>
            <li>Wed</li>
            <li>Thu</li>
            <li>Fri</li>
            <li>Sat</li>
            <li>Sun</li>
        </ul>
      </div>
      <ul className="days" >
        {generateCalendarDays()}
      </ul>
    </div>
  );
};

export default Calendar;
