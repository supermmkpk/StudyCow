import "./styles/StudyList.css";
import StudyRoomItem from "./StudyRoomItem";
import { Link } from "react-router-dom";
import useStudyStore from "../../stores/study";
import { useState, useEffect } from "react";
import { Container, Typography, Paper, Box, Avatar } from "@mui/material";
import { keyframes } from "@emotion/react";

// 랭킹 이미지들
import firstPlaceImg from "../StudyRoom/img/firstPlaceImg.png";
import secondPlaceImg from "../StudyRoom/img/secondPlaceImg.png";
import thirdPlaceImg from "../StudyRoom/img/thirdPlaceImg.png";

// 슬라이딩 애니메이션을 위한 keyframes를 정의
const slide = keyframes`
  0% { transform: translateY(0); }
  100% { transform: translateY(-100%); }
`;

const StudyList = () => {
  const {
    rooms,
    recentRoom,
    fetchRooms,
    fetchRecentRoom,
    yesterdayRankInfo,
    fetchYesterdayRankInfo,
  } = useStudyStore((state) => ({
    rooms: state.rooms,
    recentRoom: state.recentRoom,
    fetchRooms: state.fetchRooms,
    fetchRecentRoom: state.fetchRecentRoom,
    yesterdayRankInfo: state.yesterdayRankInfo,
    fetchYesterdayRankInfo: state.fetchYesterdayRankInfo,
  }));

  useEffect(() => {
    fetchRooms();
    fetchRecentRoom();
    fetchYesterdayRankInfo;
  }, [fetchRooms, fetchRecentRoom, fetchYesterdayRankInfo]);

  return (
    <div className="studyListContainer">
      <Link to="/study/create">
        <button className="studyCreateBtn">방 생성</button>
      </Link>
      <div>
        <p className="rankTitle">누적 공부시간 랭킹</p>
        <div className="rankChange"></div>
        <div className="myRank"></div>
      </div>
      <div className="recentStudyRoom">
        <p className="recentEnterTitle">최근 입장한 스터디룸</p>
        {recentRoom ? (
          <StudyRoomItem
            roomId={recentRoom.id}
            title={recentRoom.roomTitle}
            thumb={recentRoom.roomThumb}
            maxPerson={recentRoom.roomMaxPerson}
            nowPerson={recentRoom.roomNowPerson}
          />
        ) : (
          <div>
            <p>최근 접속한 방이 없소!</p>
          </div>
        )}
      </div>
      <div className="studyRoomList">
        <p className="studyListTitle">스터디룸 목록</p>
        {rooms && rooms.length > 0 ? (
          <div className="studyRoomGrid">
            {rooms.map((room) => (
              <StudyRoomItem
                key={room.id}
                roomId={room.id}
                title={room.title}
                thumb={room.thumb}
                maxPerson={room.maxPerson}
                nowPerson={room.nowPerson}
              />
            ))}
          </div>
        ) : (
          <div>
            <p>만들어진 방이 없소!</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default StudyList;
