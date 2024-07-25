import './styles/MyPageGrade.css'

function MyPageGrade() {
    return (
    <>
    <div className='gradeContents'>
        <div className="gradeItems">
          <div className='gradeItem'>
            <div className='gradeItemTitle'>
            <p>내 등급</p>
            </div>
            <div className="gradeItemContent">
            내용을 입력해주세요.
            </div>
          </div>
          <div className='gradeItem'>
            <div className='gradeItemTitle'>
            <p>공부시간</p>
            </div>
            <div className="gradeItemContent">
            내용을 입력해주세요.
            </div>
          </div>
        </div>
        <div className="expLog">
        <div className="expLogTitle">
            <p>경험치 획득 내역</p>
          </div>
          <div className="expLogContent">
            내용을 입력해주세요.
          </div>
        </div>
    </div>
    </>
  );
}

export default MyPageGrade;
