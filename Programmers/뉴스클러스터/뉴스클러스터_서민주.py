from collections import Counter

# 해당 글자가 영문자인지 판별하는 함수
def is_alphabet(s):
    if 65<=ord(s)<=90 or 97<=ord(s)<=122:
        return True
    return False

# 문자열별 집합 생성
def generate_cluster(st):
    result = []
    for i in range(len(st)-1):
        if is_alphabet(st[i]) and is_alphabet(st[i+1]):
            # 대소문자 차이 무시 위해 전부 소문자로 바꾸기
            result.append(st[i:i+2].lower())
    return result

def solution(str1, str2):
    answer = 0
    answer1 = generate_cluster(str1)
    answer2 = generate_cluster(str2)
    
    count1 = Counter(answer1)
    count2 = Counter(answer2)
    
    inter = list((count1&count2).elements())
    union = list((count1|count2).elements())
    
    # 모두 공집합인 경우 65536 return하기
    if len(answer1)==0 and len(answer2)==0:
        return 65536
    
    return int(len(inter)/len(union)*65536)