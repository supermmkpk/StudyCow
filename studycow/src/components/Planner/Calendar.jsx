import React, { useState } from 'react';
import './styles/Calendar.css'

const Calendar = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [selectedDate, setSelectedDate] = useState(null);

  const daysInMonth = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0).getDate();
  const firstDayOfMonth = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1).getDay();

  const weekDays = ['일', '월', '화', '수', '목', '금', '토'];

  const handlePrevMonth = () => {
    setCurrentDate(new Date(currentDate.getFullYear(), currentDate.getMonth() - 1, 1));
  };

  const handleNextMonth = () => {
    setCurrentDate(new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1));
  };

  const handleDateClick = (day) => {
    setSelectedDate(new Date(currentDate.getFullYear(), currentDate.getMonth(), day));
  };

  const isToday = (day) => {
    const today = new Date();
    return day === today.getDate() &&
           currentDate.getMonth() === today.getMonth() &&
           currentDate.getFullYear() === today.getFullYear();
  };

  // 날짜를 'yyyy-mm-dd' 포맷으로 변환하는 함수
  const formatDate = (date) => {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더함
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  };

  const getDayStyles = (day) => {
    const isSelected = selectedDate &&
      selectedDate.getDate() === day &&
      selectedDate.getMonth() === currentDate.getMonth() &&
      selectedDate.getFullYear() === currentDate.getFullYear();

    return {
      '--day-bg-color': isSelected ? '#BBD0E9' : '#f8f8f8',
      '--day-color': isSelected ? 'white' : isToday(day) ? '#BBD0E9' : 'black',
      '--day-border': isToday(day) ? '2px solid #BBD0E9' : 'none',
      '--day-hover-bg-color': isSelected ? '#BBD0E9' : '#e6f2ff'
    };
  };

  return (
    <>
      <div className="Calendar">
        <div className="Header">
          <button className="Button" onClick={handlePrevMonth}>&lsaquo;</button>
          <div className="MonthYear">
            {currentDate.toLocaleString('default', { month: 'long', year: 'numeric' })}
          </div>
          <button className="Button" onClick={handleNextMonth}>&rsaquo;</button>
        </div>
        <div className="Grid">
          {weekDays.map(day => <div className="DayBase WeekDay" key={day}>{day}</div>)}
          {Array.from({ length: firstDayOfMonth }).map((_, index) => (
            <div className="DayBase Day" key={`empty-${index}`} />
          ))}
          {Array.from({ length: daysInMonth }).map((_, index) => {
            const day = index + 1;
            return (
              <div
                className="DayBase Day"
                key={day}
                style={getDayStyles(day)}
                onClick={() => handleDateClick(day)}
              >
                {day}
              </div>
            );
          })}
        </div>
      </div>
      {selectedDate && (
        <div className="SelectedDate">
          선택한 날짜: {formatDate(selectedDate)}
        </div>
      )}
  </>
  );
};

export default Calendar;
