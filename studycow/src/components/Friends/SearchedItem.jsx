import "./styles/SearchedItem.css";

const SearchedItem = ({ friend }) => {
  return (
    <div className="searchedItem">
      <div>
        <p className="searchedItemInfo">
          {friend.userNickName} ({friend.userEmail})
        </p>
      </div>
      <button className="addFriendBtn">친구 추가</button>
    </div>
  );
};

export default SearchedItem;
