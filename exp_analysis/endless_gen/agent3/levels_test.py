import os

def split_file_by_semicolon(input_file, output_dir):
    # 파일 전체 읽기
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()

    # 세미콜론으로 나누기
    parts = content.split(';')

    # 파일로 나눠서 저장
    for i, part in enumerate(parts):
        filename = f"agent3_level_{i+1}.txt"
        file_path = os.path.join(output_dir, filename)

        with open(filename, 'w', encoding='utf-8') as f_out:
            # 끝에 세미콜론 다시 추가 (마지막은 빼도 됨)
            if i < len(parts) - 1:
                f_out.write(part.strip() )
            else:
                f_out.write(part.strip())
        print(f"Saved {filename}")

# 사용 예시
split_file_by_semicolon('C:/Users/hbsss/Downloads/MFEDRL-master_250601/MFEDRL-master/exp_analysis/endless_gen/agent3/agent3Levels.smblvs', "C:/Users/hbsss/Downloads/MFEDRL-master_250601/MFEDRL-master/levels/agent1_lvls")
