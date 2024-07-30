import { Route, Routes } from "react-router-dom";

// 메인페이지
import MainPage from "../views/MainPage.jsx";
// 비로그인
import Main_Unlogin from "../components/MainPage/Main_Unlogin.jsx";

// 로그인/회원가입페이지
import SignPage from "../views/SignPage.jsx";

// 마이페이지 - 메인
import MyPage from "../views/MyPage.jsx";
// 마이페이지 - 세부 페이지
import MyPageEdit from "../components/ChangeInfo/ChangeInfo.jsx";
import FriendComponent from "../components/Friends/FriendComponent";
import MyPageGrade from "../components/MyPage/MyPageGrade.jsx";

// 플래너페이지 - 개인
import PlanPage from "../views/PlanPage.jsx";
import PlanCreate from "../components/Planner/CreateModify/PlanCreate.jsx";

const PageRoutes = () => (
  <Routes>
    {/* 마이페이지 */}
    <Route path="/myaccount" element={<MyPage />}>
      <Route path="Edit" element={<MyPageEdit />} />
      <Route path="friends" element={<FriendComponent />} />
      <Route path="grade" element={<MyPageGrade />} />
    </Route>

    {/* 홈 */}
    <Route path="/" element={<MainPage />}>
      <Route path="" element={<Main_Unlogin />} />
    </Route>

    {/* 로그인 */}
    <Route path="/login" element={<SignPage />}></Route>

    {/* 캠스터디 */}
    <Route path="/study" element={<a>study</a>}></Route>

    {/* 플랜 */}
    <Route path="/plan" element={<PlanPage />}></Route>

    <Route path="/Create" element={<PlanCreate />}></Route>

    {/* 성적분석 */}
    <Route path="/analyze" element={<a>analyze</a>}></Route>
  </Routes>
);

export default PageRoutes;
