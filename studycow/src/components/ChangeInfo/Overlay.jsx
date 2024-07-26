import React from "react";
import "./Styles/Overlay.css";

const Overlay = ({ setRightPanelActive, isAnimating }) => {
  return (
    <div className={`overlay-container ${isAnimating ? "animating" : ""}`}>
      <button
        className={`overlay-button ${isAnimating ? "animate" : ""}`}
        onClick={setRightPanelActive}
      >
        비밀번호 변경
      </button>
    </div>
  );
};

export default Overlay;
