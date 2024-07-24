import os
import openai
import subprocess

openai.api_key = os.getenv("OPENAI_API_KEY")

def get_changed_files():
    result = subprocess.run(['git', 'diff', '--name-only', 'HEAD~1'], stdout=subprocess.PIPE)
    return result.stdout.decode('utf-8').split()

def review_code(file_path, code):
    response = openai.Completion.create(
        engine="text-davinci-003",
        prompt=f"Please review the following {file_path.split('.')[-1]} code:\n\n{code}",
        max_tokens=500
    )
    return response.choices[0].text

def main():
    changed_files = get_changed_files()
    for file_path in changed_files:
        if file_path.endswith(('.java', '.js', '.jsx')):  # Java, JavaScript, JSX 파일만 리뷰
            print(f"Reviewing {file_path}...")
            with open(file_path, "r") as file:
                code = file.read()
            review = review_code(file_path, code)
            print(f"Review for {file_path}:\n{review}")

if __name__ == "__main__":
    main()
