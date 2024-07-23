import React, { useEffect, useState } from "react";
import cowDribbble from "../assets/cow_dribbble.gif";
import "../styles/LoadingPage.css";

const LoadingPage = () => {
  const [dots, setDots] = useState("");

  useEffect(() => {
    const interval = setInterval(() => {
      setDots((prevDots) => {
        if (prevDots.length < 3) {
          return prevDots + ".";
        } else {
          return "";
        }
      });
    }, 500); // 0.5초마다 상태 업데이트

    return () => clearInterval(interval); // 컴포넌트 언마운트 시 인터벌 정리
  }, []);

  return (
    <div className="LoadingPage">
      <img src={cowDribbble} alt="Loading GIF" />
      <h1>열심히 가고있소{dots}</h1>
    </div>
  );
};

export default LoadingPage;
