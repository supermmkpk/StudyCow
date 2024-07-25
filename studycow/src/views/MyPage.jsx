import '../styles/MyPage.css'
import Sidebar from '../components/Sidebar/Sidebar'
import { Outlet } from 'react-router-dom';

function MyPage() {
    return (
    <>
    <div className='MyPage'>
      <div className="MyPageContainer">
        <div className="MyPageSidebar">
          <Sidebar/>
        </div>
        <div className="MyPageContent">
          <Outlet />
        </div>
      </div>  
    </div>
    </>
  );
}

export default MyPage;
