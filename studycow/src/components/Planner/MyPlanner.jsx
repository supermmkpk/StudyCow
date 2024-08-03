import React, { useState, useEffect } from 'react';
import Calendar from "./Calendar.jsx";
import PlanList from "./PlanList";
import CatPlanList from './CatPlanList.jsx';
import usePlanStore from '../../stores/plan.js';
import addButton from './img/createButton.png';
import { Link } from "react-router-dom";
import './styles/MyPlanner.css';

const sub_code_dic = {
  1: "국어",
  2: "수학",
  3: "영어",
  4: "한국사",
  5: "사회탐구",
  6: "과학탐구",
  7: "직업탐구",
  8: "제2외국어/한문",
};

const MyPlanner = () => {
  const { date, subCode, filterPlansBySubCode } = usePlanStore((state) => ({
    date: state.date,
    subCode: state.subCode,
    filterPlansBySubCode: state.filterPlansBySubCode,
  }));

  const [selectedSubject, setSelectedSubject] = useState("");

  useEffect(() => {
    const foundCode = Object.keys(sub_code_dic).find(key => sub_code_dic[key] === selectedSubject);
    filterPlansBySubCode(foundCode)
    const catPlanContent = document.querySelector('.catPlanContent');
    catPlanContent.scrollTop = 0;
  }, [selectedSubject, filterPlansBySubCode]);

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
      <div className='catPlanContainer'>
        <div className='catPlanHeader'>
          <div className="catSelectbar">
            <select
              id="subject"
              name="subject"
              onChange={(e) => setSelectedSubject(e.target.value)}
              value={selectedSubject}
              className="form-control ml-2"
            >
              <option value="" disabled hidden>
                과목 선택
              </option>
              {Object.entries(sub_code_dic).map(([key, value]) => (
                <option key={key} value={value}>
                  {value}
                </option>
              ))}
            </select>
          </div>
        </div>
        <div className="catPlanTitle">
            <p>{subCode ? sub_code_dic[subCode] : "과목을 선택하세요"}</p>
          </div>
        <div className="catPlanbox">
          <div className="catPlanContent">
            <CatPlanList />
          </div>
        </div>
      </div>
    </>
  );
};

export default MyPlanner;
