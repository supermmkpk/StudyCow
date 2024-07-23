import React from "react";

const Overlay = ({ setRightPanelActive }) => {
  return (
    <div className="overlay-container">
      <div className="overlay">
        <div className="overlay-panel overlay-left">
          <h2>이미 가입했소?</h2>
          <p>이쪽으로 오소</p>
          <button
            className="ghost"
            id="signIn"
            onClick={() => setRightPanelActive(false)}
          >
            Sign In
          </button>
        </div>
        <div className="overlay-panel overlay-right">
          <h2>아직도 회원이 아니였소?</h2>
          <p>어서 가입하소</p>
          <button
            className="ghost"
            id="signUp"
            onClick={() => setRightPanelActive(true)}
          >
            Sign Up
          </button>
        </div>
      </div>
    </div>
  );
};

export default Overlay;
