import React, {useState} from "react";
import RoomNav from './RoomNav.jsx'
import RoomSidebar from "./RoomSidebar.jsx";
import RoomContent from './RoomContent.jsx'
import RoomChat from "./RoomChat.jsx";
import RoomPlanner from "./RoomPlanner.jsx";
import './styles/StudyRoom.css'
import useStudyStore from "../../stores/study.js";
import usePlanStore from "../../stores/plan.js";
import { useParams } from 'react-router-dom';
import RoomTimer from "./RoomTimer.jsx";

function StudyRoom() {
  const {showChat, showList, showLank } = useStudyStore();
  const todayPlans = usePlanStore(state => state.todayPlans);

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);


  console.log(todayPlans)

  // URL에서 roomId 추출
  const { roomId } = useParams();


  return (
    <>
    <div className="studyRoomHeader">
      <RoomNav />
    </div>
    <div className="studyRoomMain">
        <div className="studyRoomSidebar">
            <RoomSidebar />
        </div>
        <div className="studyRoomCamContainer">
          <button onClick={openModal}>타이머 열기</button>
          <RoomTimer isOpen={isModalOpen} onClose={closeModal} />
        </div>
            <div>
            {showList && (
            <div style={{ position: 'fixed', top: '100px', right: '330px', width: '300px', zIndex: 1000 }}>
              <RoomPlanner />
            </div>
          )}
          {showLank && (
            <div style={{ position: 'fixed', bottom: '370px', right: '20px', width: '300px', zIndex: 1000 }}>
            </div>
          )}
            {showChat && (
            <div style={{ position: 'fixed', bottom: '70px', right: '20px', width: '300px', zIndex: 1000 }}>
              <RoomChat roomId={roomId} />
            </div>
          )}
        </div>
    </div>   
    </>
  );
}
export default StudyRoom;

