import React, {useEffect} from 'react';
import Navbar from "../components/Navbar/Navbar";
import Calendar from "../components/Planner/Calendar.jsx";
import PlanList from "../components/Planner/PlanList";
import usePlanStore from "../stores/plan.js";
import '../styles/PlanPage.css'
import addButton from'../components/Planner/img/createButton.png'

const PlanPage = () => {
  const date = usePlanStore(state => state.date);
  
  return (
    <>
      <Navbar />
      <div className="planerMain">
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
                  <img className="addButton" src={addButton} alt="삭제버튼" />
                </button>
            </div>
            <div className="datePlanContent">
             <PlanList className="planListItem" />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default PlanPage;
