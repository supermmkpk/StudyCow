import React from "react";
import "./styles/CreateModify.css";

const Modal = ({ closeModal }) => {
  return (
    <div className="CreateModify-modal-overlay">
      <div className="CreateModify-modal-content">
        <h2>플래너 생성</h2>
        <form className="CreateModify-modal-form">
          <div className="CreateModify-form-group">
            <label htmlFor="subject">과목</label>
            <input type="text" id="subject" name="subject" />
          </div>
          <div className="CreateModify-form-group">
            <label htmlFor="subSubject">세부과목</label>
            <input type="text" id="subSubject" name="subSubject" />
          </div>
          <div className="CreateModify-form-group">
            <label htmlFor="estimatedTime">예상시간</label>
            <input type="text" id="estimatedTime" name="estimatedTime" />
          </div>
          <div className="CreateModify-form-group">
            <label htmlFor="content">내용</label>
            <textarea id="content" name="content"></textarea>
          </div>
          <div className="CreateModify-form-buttons">
            <button type="submit" className="CreateModify-register-button">
              등록
            </button>
            <button type="button" className="CreateModify-autocomplete-button">
              자동완성
            </button>
            <button
              type="button"
              onClick={closeModal}
              className="CreateModify-cancel-button"
            >
              취소
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Modal;
