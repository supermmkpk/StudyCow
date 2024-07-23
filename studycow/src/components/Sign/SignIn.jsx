import React, { useState } from "react";

const SignIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSignIn = async (e) => {
    e.preventDefault();

    setError("");
    console.log("로그인 시도:", email, password); // 입력된 이메일과 비밀번호 출력

    try {
      const response = await fetch(
        `http://주소추후연결하기/users?userEmail=${email}`
      );
      const data = await response.json();

      console.log("서버 응답:", data); // 서버 응답 출력

      if (data.length > 0) {
        const user = data[0];
        if (user.userPassword === password) {
          console.log("로그인 성공", user); // 로그인 성공 메시지 출력
          localStorage.setItem("token", user.token);
          localStorage.setItem("userId", user.id);
          // 로그인 성공 후 처리 (예: 페이지 리디렉션)
          window.location.href = "/home"; // 예: 로그인 후 홈 페이지로 리디렉션
        } else {
          setError("로그인에 실패했습니다. 이메일 또는 비밀번호를 확인하세요.");
          console.log("로그인 실패: 잘못된 비밀번호"); // 로그인 실패 메시지 출력
        }
      } else {
        setError("로그인에 실패했습니다. 이메일 또는 비밀번호를 확인하세요.");
        console.log("로그인 실패: 잘못된 이메일"); // 로그인 실패 메시지 출력
      }
    } catch (error) {
      setError("네트워크 오류가 발생했습니다.");
      console.error("네트워크 오류:", error); // 네트워크 오류 메시지 출력
    }
  };

  return (
    <div className="form-container sign-in-container">
      <form onSubmit={handleSignIn}>
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
