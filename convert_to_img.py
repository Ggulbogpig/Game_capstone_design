# 레벨 텍스트 파일을 이미지 파일로 변환
# smb.py와 같은 디렉토리에서 실행
from smb import load_batch
from smb import MarioLevel

# 레벨 불러오기
with open("exp_analysis/endless_gen/agent6/agent6Levels_copy_1.smblvs", "r") as f:
    content = f.read()
levels = [MarioLevel(c) for c in content.split("\n;\n")]

# 첫 번째 레벨을 이미지로 저장
levels[0].to_img('tilemaps/agent6Levels.png')