import Navbar from "react-bootstrap/Navbar";
import Logo from "./BrandLogo.jsx";
import './styles/Sidebar.css'
import profileImg from'./img/UserProfile.jpg';
import useInfoStore from "../../stores/infos.js";
import LogoImg from "./img/logo.png";

function Sidebar() {
  const { userInfo } = useInfoStore();
    return (
    <>
      <div className="Sidebar">
        <div className="SidebarLogo">
          <Navbar.Brand href="/home">
            <Logo />
          </Navbar.Brand>
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
         <Navbar.Brand href="/myaccount/edit">
            회원정보 수정
          </Navbar.Brand>
          <Navbar.Brand href="/myaccount/friends">
            친구 관리
          </Navbar.Brand>
          <Navbar.Brand href="/myaccount/info">
            회원등급 정보
          </Navbar.Brand>
          <hr/>
        </div>
        <div className="SidebarFooter">
          <div className="SidebarFooterItem">
            <Navbar.Brand href="/home">
              로그아웃
            </Navbar.Brand>
            <a>/</a>
            <Navbar.Brand href="/home">
              회원탈퇴
            </Navbar.Brand>
          </div>
          <div className="SidebarFooterLogo">
            <div className="LogoItem">
              <a>공부하소 닷컴</a>
            </div>
          </div>
        </div>
      </div>

    </>
  );
}

export default Sidebar;
