import Navbar from "../components/Navbar/Navbar";
import StudyCreate from "../components/StudyCreate/StudyCreate";
import "../styles/StudyPage.css";

const StudyPage = () => {
  return (
    <>
      <Navbar />
      <div className="main">
        <StudyCreate />
      </div>
    </>
  );
};

export default StudyPage;
