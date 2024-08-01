import React from "react";
import RoomNav from './RoomNav.jsx'
import RoomSidebar from "./RoomSidebar.jsx";
import RoomContent from './RoomContent.jsx'
import './styles/StudyRoom.css'

function StudyRoom() {
  return (
    <>
    <div className="studyRoomHeader">
      <p>스터디룸 내부 페이지 테스트</p>
      <RoomNav />
    </div>
    <div className="studyRoomMain">
        <div className="studyRoomSidebar">
            <RoomSidebar />
        </div>
        <div className="studyRoomCamContainer">
          <RoomContent />
        </div>
    </div>
      
    </>
  );
}
export default StudyRoom;

