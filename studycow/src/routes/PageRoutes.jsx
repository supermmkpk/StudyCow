import { Route, Routes } from 'react-router-dom';


// 로그인/회원가입페이지
import SignPage from '../views/SignPage.jsx'


// 마이페이지 - 메인
import MyPage from '../views/MyPage.jsx';
// 마이페이지 - 세부 페이지
import MyPageEdit from '../components/MyPage/MyPageEdit.jsx';
import FriendComponent from "../components/Friends/FriendComponent";
import MyPageGrade from '../components/MyPage/MyPageGrade.jsx';

const PageRoutes = () => (
  <Routes> 
    {/* 마이페이지 */}
    <Route path="/myaccount" element={<MyPage />}>
        <Route path="edit" element={<MyPageEdit />} />
        <Route path="friends" element={<FriendComponent/>} />
        <Route path="grade" element={<MyPageGrade/>} />
    </Route>

    {/* 홈 */}
    <Route path="/" element={<a>home</a>}>
    </Route>

    {/* 로그인 */}
    <Route path="/login" element={<SignPage />}>
    </Route>

    {/* 캠스터디 */}
    <Route path="/study" element={<a>study</a>}>
    </Route>
    
    {/* 플랜 */}
    <Route path="/plan" element={<a>plan</a>}>
    </Route>

    {/* 성적분석 */}
    <Route path="/analyze" element={<a>analyze</a>}>
    </Route>
  </Routes>
);

export default PageRoutes;
