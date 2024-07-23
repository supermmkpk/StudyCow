import '../styles/MyPage.css'
import Sidebar from '../components/Sidebar/Sidebar'

function MyPage() {
    return (
    <>
    <div className='MyPage'>
      <div className="MyPageContainer">
        <div className="MyPageSidebar">
          <Sidebar/>
        </div>
        <div className="MyPageContent">
          <h1>프로필 페이지</h1>
          <p>여기에 프로필 정보를 입력하세요.</p>
        </div>
      </div>  
    </div>
    </>
  );
}

export default MyPage;
