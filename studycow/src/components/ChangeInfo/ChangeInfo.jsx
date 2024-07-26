import React, { useState } from "react";
import ChangeMyInfo from "./ChangeMyInfo";
import ChangePassword from "./ChangePassword";
import Overlay from "./Overlay";
import "./Styles/ChangeInfo.css";

const ChangeInfo = () => {
  const [isPasswordChange, setIsPasswordChange] = useState(false);
  const [isAnimating, setIsAnimating] = useState(false);

  const handleButtonClick = () => {
    setIsAnimating(true);
    setTimeout(() => {
      setIsPasswordChange(true);
      setIsAnimating(false);
    }, 600); // Match the duration of the animation
  };

  const handleCancelClick = () => {
    setIsPasswordChange(false);
  };

  return (
    <div className="change-info-container">
      {!isPasswordChange && (
        <Overlay
          setRightPanelActive={handleButtonClick}
          isAnimating={isAnimating}
        />
      )}
      <div
        className={`form-container ${isPasswordChange ? "next-form" : ""} ${
          isAnimating ? "animate" : ""
        }`}
      >
        {isPasswordChange ? (
          <ChangePassword setIsPasswordChange={handleCancelClick} />
        ) : (
          <ChangeMyInfo />
        )}
      </div>
    </div>
  );
};

export default ChangeInfo;
