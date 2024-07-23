import Navbar from "react-bootstrap/Navbar";
import Logo from "./BrandLogo.jsx";
import "./styles/Navbar.css";
import useInfoStore from "../../stores/infos.js";
import User from "./UserInformation.jsx";

function Header() {
  const { isLogin } = useInfoStore();

  return (
    <>
      <div id="header" className="navContainer stick-top">
        <div id="navbar" className="navBackground">
          <div id="navContent" className="leftContainer">
            {isLogin && (
              <>
                <Navbar.Brand href="#on">
                  <Logo />
                </Navbar.Brand>
                <Navbar.Brand className="navItem" href="#캠스터디">
                  캠 스터디
                </Navbar.Brand>
                <Navbar.Brand className="navItem" href="#플래너">
                  플래너
                </Navbar.Brand>
                <Navbar.Brand className="navItem" href="#성적 분석">
                  성적 분석
                </Navbar.Brand>
              </>
            )}
            {!isLogin && (
              <>
                <Navbar.Brand href="#off">
                  <Logo />
                </Navbar.Brand>
              </>
            )}
          </div>
          <div id="navContent" className="rightContainer">
            {!isLogin && (
              <>
                <Navbar.Brand href="#login">로그인/회원가입</Navbar.Brand>
              </>
            )}
            {isLogin && (
              <>
                <User />
              </>
            )}
          </div>
        </div>
      </div>
    </>
  );
}

export default Header;
