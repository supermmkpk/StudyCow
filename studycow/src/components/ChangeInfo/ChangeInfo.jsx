import React, { useState } from "react";
import ChangeMyInfo from "./ChangeMyInfo";
import ChangePassword from "./ChangePassword";
import ChangeButton from "./ChangeButton";
import "./Styles/ChangeInfo.css";

const ChangeInfo = () => {
  const [isPasswordChange, setIsPasswordChange] = useState(false);

  const handleButtonClick = () => {
    setIsPasswordChange(true);
  };

  const handleCancelClick = () => {
    setIsPasswordChange(false);
  };

  return (
    <div className="change-info-container">
      {!isPasswordChange && (
        <ChangeButton setRightPanelActive={handleButtonClick} />
      )}
      <div className={`form-container ${isPasswordChange ? "next-form" : ""}`}>
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
