import Navbar from "../components/Navbar/Navbar";
import { Outlet } from 'react-router-dom';

const MainPage = () => {
  return (
    <>
      <Navbar />
      <div className="main">
        <Outlet />
      </div>
    </>
  );
};

export default MainPage;
