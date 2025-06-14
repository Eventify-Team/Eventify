import {useState, useEffect} from "react";
import "../App.css"; 
import icon from"../Images/blue.png"

const Calendar = ({ eventDates }) => {

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

  const [events, setEventDates] = useState([]);

  useEffect(() => {
    setEventDates(eventDates.map(str => {
      const [year, month, day] = str.split("-").map(Number);
      return new Date(year, month - 1, day);
    }));
  }, [eventDates]);

  const generateCalendarDays = () => {
    
    let days = [
    ];

    for (let i = firstDayOfMonth === 0 ? 6 : firstDayOfMonth - 1; i > 0; i--) {
      days.push(<li className="inactive">{lastDateOfLastMonth - i + 1}</li>);
      
    }

    for (let i = 1; i <= lastDateOfMonth; i++) {

      days.push(
        <li style={{ backgroundImage: (events.some(event =>
          event.getFullYear() === currYear &&
          event.getMonth() === currMonth &&
          event.getDate() === i)) ? `url(${icon})` : '', color: "black", backgroundSize: '70px', backgroundRepeat: 'no-repeat', backgroundPosition: 'center'}}>

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
    <div className="calendar-container shadow">
      <div className="header" style={{backgroundColor: '#145DA0'}}>
        <span className="prevnext shadow" onClick={handlePrev} id="prev" style={{borderRadius: '50%', width: '20px', textAlign: 'center', backgroundColor: '#2E8BC0'}}>&#8249;</span>
        <span className="this-month">{months[currMonth]} {currYear}</span>
        <span className="prevnext shadow" onClick={handleNext} id="next" style={{borderRadius: '50%', width: '20px', textAlign: 'center', backgroundColor: '#2E8BC0'}}>&#8250;</span>
      </div>
      <div className="daysgrid">
        <ul className="daysList" style={{backgroundColor: '#2E8BC0'}}>
            <li>Mon</li>
            <li>Tue</li>
            <li>Wed</li>
            <li>Thu</li>
            <li>Fri</li>
            <li>Sat</li>
            <li>Sun</li>
        </ul>
        <ul className="days" style={{color: '#2E8BC0'}}>
              {generateCalendarDays()}
        </ul>
         
      </div>
    </div>
  );
};

export default Calendar;