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
    <div className="ChangeInfo-full-page-container">
      <div className="ChangeInfo-container">
        {!isPasswordChange && (
          <ChangeButton onButtonClick={handleButtonClick} />
        )}
        <div
          className={`ChangeInfo-form-container ${
            isPasswordChange ? "next-form" : ""
          }`}
        >
          {isPasswordChange ? (
            <ChangePassword onCancelClick={handleCancelClick} />
          ) : (
            <ChangeMyInfo />
          )}
        </div>
      </div>
    </div>
  );
};

export default ChangeInfo;
