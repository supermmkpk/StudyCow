import Navbar from "react-bootstrap/Navbar";
import Logo from "./BrandLogo.jsx";
import "./styles/Navbar.css";
import useInfoStore from "../../stores/infos.js";
import User from "./UserInformation.jsx";
import { Link } from "react-router-dom";

function Header() {
  const { isLogin } = useInfoStore();

  return (
    <>
      <div id="header" className="navContainer stick-top">
        <div id="navbar" className="navBackground">
          <div id="navContent" className="leftContainer">
            {isLogin && (
              <>
                <Link to="/">
                  <Logo />
                </Link>
                <Link className="navItem" to="/study">
                  <p>캠 스터디</p>
                </Link>
                <Link className="navItem" to="/plan">
                  <p>플래너</p>
                </Link>
                <Link className="navItem" to="/analyze">
                  <p>성적 분석</p>
                </Link>
              </>
            )}
            {!isLogin && (
              <>
                <Link to="/">
                  <Logo />
                </Link>
              </>
            )}
          </div>
          <div id="navContent" className="rightContainer">
            {!isLogin && (
              <>
                <Link to="/login" className="loginItem"><p>로그인/회원가입</p></Link>
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
