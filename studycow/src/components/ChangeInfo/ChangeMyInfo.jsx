import React, { useState, useEffect } from "react";
import axios from "axios";
import useInfoStore from "../../stores/infos"; // store 경로에 맞게 수정하세요
import "./Styles/ChangeInfo.css";

const API_URL = "http://localhost:8080/studycow/user/me";

const ChangeMyInfo = () => {
  const { token, userInfo, setUserInfo } = useInfoStore((state) => ({
    token: state.token,
    userInfo: state.userInfo,
    setUserInfo: state.setUserInfo,
  }));

  const [email, setEmail] = useState("");
  const [nickname, setNickname] = useState("");
  const [thumb, setThumb] = useState(null); // 파일 객체로 변경
  const [publicStatus, setPublicStatus] = useState(0);

  useEffect(() => {
    setEmail(userInfo.userEmail || "");
    setThumb(userInfo.userThumb || null);
    setNickname(userInfo.userNickname || "");
    setPublicStatus(userInfo.userPublic || 0);
  }, [userInfo]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (!token) {
        console.error("토큰이 없습니다.");
        return;
      }

      const formData = new FormData();
      formData.append("userEmail", email);
      formData.append("userNickname", nickname);
      formData.append("userPublic", publicStatus);
      if (thumb) {
        formData.append("userThumb", thumb);
      }

      console.log("보내는 데이터:", formData);
      console.log("토큰:", token);

      const response = await axios.patch(API_URL, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "multipart/form-data",
        },
      });

      console.log("서버 응답:", response);

      if (response.status === 200) {
        setUserInfo({
          userEmail: email,
          userThumb: thumb ? URL.createObjectURL(thumb) : userInfo.userThumb,
          userNickname: nickname,
          userPublic: publicStatus,
        });
        alert("회원정보가 성공적으로 변경되었습니다.");
      } else {
        throw new Error("회원정보 변경에 실패했습니다.");
      }
    } catch (error) {
      console.error("Error updating user info:", error);
      alert("회원정보 변경에 실패했습니다.");
    }
  };

  const handleFileChange = (e) => {
    setThumb(e.target.files[0]);
  };

  return (
    <div className="ChangeInfo-info-form">
      <h2>회원정보 변경</h2>
      <form onSubmit={handleSubmit}>
        <div className="ChangeInfo-form-group">
          <label htmlFor="email">이메일:</label>
          <input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="ChangeInfo-form-group">
          <label htmlFor="nickname">닉네임:</label>
          <input
            type="text"
            id="nickname"
            name="nickname"
            value={nickname}
            onChange={(e) => setNickname(e.target.value)}
          />
        </div>
        <div className="ChangeInfo-form-group">
          <label htmlFor="thumb">프로필 이미지:</label>
          <input
            type="file"
            id="thumb"
            name="thumb"
            onChange={handleFileChange}
          />
        </div>
        <input
          type="hidden"
          id="publicStatus"
          name="publicStatus"
          value={publicStatus}
          onChange={(e) => setPublicStatus(Number(e.target.value))}
        />
        <div className="ChangeInfo-button-group">
          <button type="submit" className="ChangeInfo-submit-button">
            변경
          </button>
        </div>
      </form>
    </div>
  );
};

export default ChangeMyInfo;
