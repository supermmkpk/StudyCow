import React from "react";
import { Outlet, Navigate } from 'react-router-dom';
import useInfoStore from '../stores/infos';
import SignIn from "../components/Sign/SignIn";
import SignUp from "../components/Sign/SignUp";
import Overlay from "../components/Sign/Overlay";
import "../components/Sign/styles/Sign.css";

const Sign = () => {
  const [rightPanelActive, setRightPanelActive] = React.useState(false);
  const { isLogin } = useInfoStore();

  if (isLogin) {
    return <Navigate to="/" />;  // 로그인 상태라면 메인페이지로 redirect
  } 

  return (
    <div
      className={`container ${rightPanelActive ? "right-panel-active" : ""}`}
      id="container"
    >
      <SignUp />
      <SignIn />
      <Overlay setRightPanelActive={setRightPanelActive} />
    </div>
  );
};

export default Sign;
