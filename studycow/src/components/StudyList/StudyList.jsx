import "./styles/StudyList.css";
import StudyRoomItem from "./StudyRoomItem";
import { Link } from "react-router-dom";

const StudyList = () => {
  // return 전의 아래 코드는 나중에 API로 방 정보와 개수를 받아와 수정할 예정입니다
  const rooms = Array(5)
    .fill()
    .map((_, index) => ({ id: index + 1, name: `Room ${index + 1}` }));

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
        <StudyRoomItem />
      </div>
      <div className="studyRoomList">
        <p className="studyListTitle">스터디룸 목록</p>
        <div className="studyRoomGrid">
          {rooms.map((room) => (
            <StudyRoomItem key={room.id} room={room} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default StudyList;
