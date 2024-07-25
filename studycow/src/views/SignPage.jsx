import React from "react";
import SignIn from "../components/Sign/SignIn";
import SignUp from "../components/Sign/SignUp";
import Overlay from "../components/Sign/Overlay";
import "../components/Sign/styles/Sign.css";

const Sign = () => {
  const [rightPanelActive, setRightPanelActive] = React.useState(false);

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
