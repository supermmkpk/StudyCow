import React, { useState } from "react";

const SignUp = () => {
  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleSignUp = async (e) => {
    e.preventDefault();
    if (password !== confirmPassword) {
      setError("비밀번호가 일치하지 않소");
      setSuccess("");
      return;
    }

    setError("");
    try {
      const response = await fetch("http://주소추후연결하기", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userEmail: email,
          userPassword: password,
          userName: nickname, // nickname을 userName으로 사용
        }),
      });

      const data = await response.json();
      if (response.ok) {
        setSuccess("가입을 환영하겠소");
        setNickname("");
        setEmail("");
        setPassword("");
        setConfirmPassword("");
      } else {
        setError(data.message || "회원가입에 실패했습니다.");
        setSuccess("");
      }
    } catch (error) {
      setError("네트워크 오류가 발생했습니다.");
      setSuccess("");
    }
  };

  return (
    <div className="form-container sign-up-container">
      <form onSubmit={handleSignUp}>
        <h1>Sign Up</h1>

        <input
          type="text"
          placeholder="닉네임"
          value={nickname}
          onChange={(e) => setNickname(e.target.value)}
          required
        />
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
        <input
          type="password"
          placeholder="비밀번호 (확인)"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          required
        />
        {error && <p className="error">{error}</p>}
        {success && <p className="success">{success}</p>}
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
};

export default SignUp;
