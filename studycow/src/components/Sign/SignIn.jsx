import React, { useState } from "react";
import useInfoStore from "../../stores/infos";

const SignIn = () => {
  const { sendLoginRequest } = useInfoStore();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const success = await sendLoginRequest(email, password);
    if (success) {
      alert("로그인 성공!");
    } else {
      alert("로그인 실패!");
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
