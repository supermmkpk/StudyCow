import "./styles/FriendSearch.css";

const FriendSearch = () => {
  return (
    <div>
      <p className="FriendsTitle">친구 검색</p>
      <div className="searchBox">
        <div className="searchBar">
          <input
            className="searchInput"
            type="text"
            placeholder="검색하고자 하는 친구의 이름을 입력하세요."
          />
          <button className="searchBtn">찾아보기</button>
        </div>
        <div className="friendsList">친구목록 나올 영역</div>
      </div>
    </div>
  );
};

export default FriendSearch;
