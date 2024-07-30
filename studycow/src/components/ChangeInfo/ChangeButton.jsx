import React from "react";
import "./Styles/ChangeButton.css";

const ChangeButton = ({ setRightPanelActive, isAnimating }) => {
  return (
    <div className={`overlay-container2 ${isAnimating ? "animating" : ""}`}>
      <button
        className={`overlay-button2 ${isAnimating ? "animate" : ""}`}
        onClick={setRightPanelActive}
      >
        비밀번호 변경
      </button>
    </div>
  );
};

export default ChangeButton;
