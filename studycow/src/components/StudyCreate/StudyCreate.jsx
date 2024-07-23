import "./styles/StudyCreate.css";
import Infobox from "./Infobox";

const StudyCreate = () => {
  return (
    <div className="mainbox">
      <div>
        <div className="Info">
          <div className="Thumbnail">
            <div>
              <div className="subbox" />
              <input type="file" />
            </div>
          </div>
          <div className="InfoInput">
            <Infobox name="제목" />
            <Infobox name="인원 제한" />
            <Infobox name="스터디 주제" />
            <Infobox name="기본 목표 시간" />
            <Infobox name="BGM 목록" />
          </div>
        </div>
        <div className="DetailInfo">
          <h1>스터디 한 줄 소개</h1>
          <input
            className="DetailInfoInput"
            type="textarea"
            placeholder="스터디에 관한 간단한 설명을 입력하세요"
          />
        </div>
        <div className="btn">
          <button className="create-btn">생성하기</button>
        </div>
      </div>
    </div>
  );
};

export default StudyCreate;
