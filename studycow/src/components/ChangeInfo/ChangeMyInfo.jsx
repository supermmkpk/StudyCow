import React from "react";
import "./Styles/ChangeInfo.css";

const ChangeMyInfo = () => {
  return (
    <div className="info-form">
      <h2>회원정보 변경</h2>
      <form>
        <div className="form-group">
          <label htmlFor="email">이메일:</label>
          <input type="email" id="email" name="email" />
        </div>
        <div className="form-group">
          <label htmlFor="nickname">닉네임:</label>
          <input type="text" id="nickname" name="nickname" />
        </div>
        <div className="form-group">
          <label htmlFor="school">소속학교:</label>
          <input type="text" id="school" name="school" />
        </div>
        <div className="form-group">
          <label htmlFor="grade">학년:</label>
          <input type="text" id="grade" name="grade" />
        </div>
        <div className="button-group">
          <button type="submit" className="submit-button">
            변경
          </button>
        </div>
      </form>
    </div>
  );
};

export default ChangeMyInfo;
