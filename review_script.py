import openai
import os

openai.api_key = os.getenv("OPENAI_API_KEY")

def review_code(file_path):
    with open(file_path, "r") as file:
        code = file.read()
    response = openai.Completion.create(
        engine="text-davinci-003",
        prompt=f"Please review the following code:\n\n{code}",
        max_tokens=500
    )
    return response.choices[0].text

if __name__ == "__main__":
    # GitLab CI/CD 환경에서는 변경된 파일 목록을 사용할 수 있습니다.
    # 여기서는 예제로 하나의 파일을 지정합니다.
    review = review_code("path/to/your/codefile.py")
    print(review)
