import useInfoStore from "../../stores/infos.js";
import profileImg from "./img/7.jpg";
import upImg from "./img/up.png";
import downImg from "./img/down.png";
import "./styles/UserInformation.css";
import Navbar from "react-bootstrap/Navbar";

function UserInformation() {
  const { toggleDropdown, isOpen, userInfo, logout } = useInfoStore();

  return (
    <>
      <div className="userDropdown">
        {!isOpen && (
          <>
            <div className="userInfoClose" onClick={toggleDropdown}>
              <div>
                <img
                  className="profileImg"
                  src={profileImg}
                  alt="프로필이미지"
                />
              </div>
              <div>
                <a>
                  {userInfo.name}님
                  <br />
                  안녕하세요!
                </a>
              </div>
              <div>
                <img className="downImg" src={downImg} alt="아랫방향" />
              </div>
            </div>
          </>
        )}
        {isOpen && (
          <>
            <div className="userInfoOpen" onClick={toggleDropdown}>
              <div>
                <img
                  width="65px"
                  className="profileImg"
                  src={profileImg}
                  alt="프로필이미지"
                />
              </div>
              <div>
                <a>
                  {userInfo.name}님
                  <br />
                  안녕하세요!
                </a>
              </div>
              <div>
                <img className="upImg" src={upImg} alt="윗방향" />
              </div>
            </div>
            <div className="userDropdownContent">
              <div className="userInfoDetail">
                  <p>등급: {userInfo.grade}</p>
                  <p>경험치: {userInfo.exp}</p>
                  <p>오늘 공부시간: {userInfo.todayStudyTime}</p>
                  <p>현재 랭킹: {userInfo.ranks}</p>
                </div>
                <Navbar.Brand className="userInfoItem" href="/myaccount">
                  마이 페이지
                </Navbar.Brand>
                <Navbar.Brand className="userInfoItem" onClick={logout}>
                  로그아웃
                </Navbar.Brand>
            </div>
          </>
        )}
      </div>
    </>
  );
}

export default UserInformation;
