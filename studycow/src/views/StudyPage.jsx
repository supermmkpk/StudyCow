import Navbar from "../components/Navbar/Navbar";
import "../styles/StudyPage.css";
import { Outlet } from "react-router-dom";

const StudyPage = () => {
  return (
    <>
      <Navbar />
      <div className="main">
        <Outlet />
      </div>
    </>
  );
};

export default StudyPage;
