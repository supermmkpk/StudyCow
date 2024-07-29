import React, { useState } from "react";
import useInfoStore from "../../stores/infos";

const SignIn = () => {
  const { sendLoginRequest, token, isLogin } = useInfoStore();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error] = useState("");




  const handleSubmit = async (e) => {
    e.preventDefault();
    const success = await sendLoginRequest(email, password);
    if (success) {
      // 로그인 성공 시, 필요한 동작을 수행합니다.
      console.log(token, isLogin)
      alert('로그인 성공!');
    } else {
      // 로그인 실패 시, 에러 메시지를 표시합니다.
      alert('로그인 실패!');
    }
  };


  return (
    <div className="form-container sign-in-container">
      <form onSubmit={handleSubmit}>
        <h1>Sign in</h1>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        {error && <p className="error">{error}</p>}
        <button type="submit">Sign In</button>
      </form>
    </div>
  );
};

export default SignIn;
