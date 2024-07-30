import Navbar from "../components/Navbar/Navbar";
import React from 'react';
import Calendar from "../components/Planner/Calendar.jsx";
import PlanList from "../components/Planner/PlanList";
import '../styles/PlanPage.css'

const PlanPage = () => {
  // 예시 데이터
  const plans = [
    {
      "planId": 1,
      "userId": 6,
      "subCode": 1,
      "planDate": "2024-07-29",
      "planContent": "string",
      "planStudyTime": 5,
      "planStatus": 0
    },
    {
      "planId": 2,
      "userId": 6,
      "subCode": 2,
      "planDate": "2024-07-29",
      "planContent": "string",
      "planStudyTime": 2,
      "planStatus": 0
    }
  ];

  return (
    <>
      <Navbar />
      <div className="planerMain">
        <div className="datePlanContainer">
          <div className="dateCalendar">
            <Calendar />
          </div>
          <div className="datePlanbox">
           <PlanList className="planListItem" plans={plans} />
          </div>
        </div>
      </div>
    </>
  );
};

export default PlanPage;
