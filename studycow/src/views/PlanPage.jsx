import React from 'react';
import Navbar from "../components/Navbar/Navbar";
import '../styles/PlanPage.css'
import { Outlet } from 'react-router-dom';

const PlanPage = () => {  
  return (
    <>
      <Navbar />
      <div className="planerMain">
        <Outlet />
      </div>
    </>
  );
};

export default PlanPage;
