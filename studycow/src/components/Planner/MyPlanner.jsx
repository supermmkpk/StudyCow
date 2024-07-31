import React from 'react';
import Calendar from "./Calendar.jsx";
import PlanList from "./PlanList";
import usePlanStore from '../../stores/plan.js';
import './styles/MyPlanner.css'
import addButton from'./img/createButton.png'
import { Link } from "react-router-dom";

const MyPlanner = () => {
  const date = usePlanStore(state => state.date);
  
  return (
    <>
    <div className="datePlanContainer">
        <div className="dateCalendar">
        <Calendar />
        </div>
        <div className="datePlanbox">
        <div className="datePlanDate">
            <div className="emptyCase" />
            <div className="dateCase">
            <p>{date}</p>
            </div>
            <button className='buttonCase'>
                <Link to="create"><img className="addButton" src={addButton} alt="추가버튼" /></Link>
            </button>
        </div>
        <div className="datePlanContent">
            <PlanList className="planListItem" />
        </div>
        </div>
    </div>
    </>
  );
};

export default MyPlanner;
