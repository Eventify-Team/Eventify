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

  const firstDayOfMonth = new Date(currYear, currMonth, 1).getDay();
  const lastDateOfMonth = new Date(currYear, currMonth + 1, 0).getDate();
  const lastDayOfMonth = new Date(currYear, currMonth, lastDateOfMonth).getDay();
  const lastDateOfLastMonth = new Date(currYear, currMonth, 0).getDate();

  const generateCalendarDays = () => {
    
    let days = [
    ];

    for (let i = firstDayOfMonth === 0 ? 6 : firstDayOfMonth - 1; i > 0; i--) {
      days.push(<li key={`prev-${i}`} className="inactive">{lastDateOfLastMonth - i + 1}</li>);
    }

    for (let i = 1; i <= lastDateOfMonth; i++) {
      const isToday =
        i === new Date().getDate() &&
        currMonth === new Date().getMonth() &&
        currYear === new Date().getFullYear();

      days.push(
        <li key={`curr-${i}`} className={isToday ? "active" : ""}>
          {i}
        </li>
      );
    }

    
    for (let i = lastDayOfMonth === 0 ? 0 : 7 - lastDayOfMonth; i > 0; i--) {
    
        days.push(<li key={`next-${i}`} className="inactive">{i}</li>);
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
