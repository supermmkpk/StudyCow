import React, {useState} from "react";
import RoomNav from './RoomNav.jsx'
import RoomSidebar from "./RoomSidebar.jsx";
import RoomContent from './RoomContent.jsx'
import RoomChat from "./RoomChat.jsx";
import './styles/StudyRoom.css'
import { Button } from 'react-bootstrap';

function StudyRoom() {
  const [showChat, setShowChat] = useState(false);

  const toggleChat = () => {
    setShowChat(!showChat);
  };
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
            <div>
             <Button variant="primary" onClick={toggleChat} style={{ position: 'fixed', bottom: '20px', right: '20px' }}>
            채팅 열기
             </Button>
            {showChat && (
            <div style={{ position: 'fixed', bottom: '70px', right: '20px', width: '300px', zIndex: 1000 }}>
              <RoomChat />
            </div>
          )}
        </div>
    </div>   
    </>
  );
}
export default StudyRoom;

