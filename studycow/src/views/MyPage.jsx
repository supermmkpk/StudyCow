import "../styles/MyPage.css";
import Sidebar from "../components/Sidebar/Sidebar";
import FriendComponent from "../components/Friends/FriendComponent";

function MyPage() {
  return (
    <>
      <div className="MyPage">
        <div className="MyPageContainer">
          <div className="MyPageSidebar">
            <Sidebar />
          </div>
          <div className="MyPageContent">
            <FriendComponent />
          </div>
        </div>
      </div>
    </>
  );
}

export default MyPage;
