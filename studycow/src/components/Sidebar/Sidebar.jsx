import Navbar from "react-bootstrap/Navbar";
import Logo from "./BrandLogo.jsx";
import './styles/Sidebar.css'
import profileImg from'./img/UserProfile.jpg';
import useInfoStore from "../../stores/infos.js";
import { Link, useNavigate, Navigate } from 'react-router-dom';

function Sidebar() {
  // 상태 - 유저 정보 가져오기
  const { userInfo, isLogin, logout, resign } = useInfoStore();

  // 이동 - 홈으로 단순 이동
  const navigate = useNavigate();
  function goHome() {
   navigate('/'); 
  }
  
  if (!isLogin) {
    return <Navigate to="/login" />;  // 로그인 상태가 아니면 로그인페이지로 redirect
  } 




    return (
    <>
      <div className="Sidebar">
        <div className="SidebarLogo" onClick={goHome}>
          <Logo />
        </div>
        <div className="SidebarUserInfo" >
          <img
            width="114px"
            className="profileImg"
            src={profileImg}
            alt="프로필이미지"
          />
          <div className="SidbarUserInfoContent">
            <p>{userInfo.name}</p>
            <a>{userInfo.email}</a>
          </div>
        </div>
        <div className="SidebarContent">
         <Link to="edit">
            회원정보 수정
          </Link>
          <Link to="friends">
            친구 관리
          </Link>
          <Link to="grade">
            회원등급 정보
          </Link>
          <hr/>
        </div>
        <div className="SidebarFooter">
          <div className="SidebarFooterItem">
            <a onClick={logout}>
              로그아웃
            </a>
            <a>/</a>
            <a onClick={resign}>
              회원탈퇴
            </a>
          </div>
          <div className="SidebarFooterLogo">
            <div className="LogoItem">
              <a>공부했소?</a>
            </div>
          </div>
        </div>
      </div>

    </>
  );
}

export default Sidebar;
