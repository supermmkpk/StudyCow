import "./styles/StudyRoomItem.css";

const StudyRoomItem = () => {
  return (
    <div className="studyRoomContainer">
      <p className="studyRoomTitle">방 제목</p>
      <p className="studyRoomCount">방 인원</p>
      <button className="studyRoomEnterBtn">입장하기</button>
    </div>
  );
};

export default StudyRoomItem;
