"""
  @Time : 2022/3/10 11:15 
  @Author : Ziqi Wang
  @File : math.py 
"""
import numpy as np
from scipy.stats import entropy


def a_clip(v, g, r=1.0, mode=0):
    if mode == 0:
        return min(r, 1 - abs(v - g) / g)
    elif mode == 1:
        return min(r, v / g)
    elif mode == -1:
        return min(r, 2 - v / g)

def jsdiv(p, q):
    return (entropy(p, p + q, base=2) + entropy(q, p + q, base=2)) / 2

def grid_cnt(data, ranges, n_grids=10, normalize=True):
    eps = 1e-10
    d = data.shape[1]
    res = np.zeros([n_grids] * d)
    itvs = (ranges[:, 1] - ranges[:, 0]) * ((1 + eps) / n_grids)

    for item in data:
        indexes = tuple((item // itvs))
        res[indexes] = res[indexes] + 1
    if normalize:
        res /= res.size
    return res

def lpdist_mat(X, Y, p=2):
    diff = np.abs(np.expand_dims(X, axis=1) - np.expand_dims(Y, axis=0))
    distance_matrix = np.sum(diff ** p, axis=-1) ** (1 / p)
    return distance_matrix

if __name__ == '__main__':
    a = [[1, 2], [2, 3], [3, 4]]
    b = [[1.5, 2.5], [2.5, 3.5]]
    print(lpdist_mat(a, b))


#추가가
def easy_post_process(onehot_seg):

    """
    - 입력: onehot_seg (One-hot 인코딩된 맵, numpy.ndarray 형태)
    - 출력: 후처리된 onehot_seg
    """

    # 예: 빈 공간(air) 비율이 너무 높으면 메꿔주기
    air_threshold = 0.5  # 50% 이상 비어있으면 메꿔줌
    air_index = 0        # onehot에서 air tile index (보통 0으로 가정)

    # one-hot에서 어떤 채널이 선택됐는지 찾기
    seg_map = np.argmax(onehot_seg, axis=0)  # (height, width)

    # 각 column마다 air tile 비율 확인
    for col in range(seg_map.shape[1]):
        column = seg_map[:, col]
        air_ratio = np.sum(column == air_index) / len(column)
        if air_ratio > air_threshold:
            # 하단부만이라도 땅(ground, 보통 1번 index)으로 채워주기
            ground_index = 1
            seg_map[-2:, col] = ground_index

    # 다시 one-hot으로 변환
    for i in range(onehot_seg.shape[0]):
        onehot_seg[i] = (seg_map == i).astype(np.float32)

    return onehot_seg