import Navbar from "../components/Navbar/Navbar";
import StudyList from "../components/StudyList/StudyList";
import "../styles/StudyPage.css";

const StudyPage = () => {
  return (
    <>
      <Navbar />
      <div className="main">
        <StudyList />
      </div>
    </>
  );
};

export default StudyPage;
