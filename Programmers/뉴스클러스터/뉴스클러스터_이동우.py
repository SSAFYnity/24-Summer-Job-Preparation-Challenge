def solution(str1, str2):    
    dict1 = dict()
    for i in range(1, len(str1)):
        if str1[i - 1:i + 1].isalpha():
            if str1[i - 1:i + 1].lower() in dict1.keys():
                dict1[str1[i - 1:i + 1].lower()] += 1
            else:
                dict1[str1[i - 1:i + 1].lower()] = 1
    dict2 = dict()
    for i in range(1, len(str2)):
        if str2[i - 1:i + 1].isalpha():
            if str2[i - 1:i + 1].lower() in dict2.keys():
                dict2[str2[i - 1:i + 1].lower()] += 1
            else:
                dict2[str2[i - 1:i + 1].lower()] = 1
                
    cnt_int = 0
    cnt_uni = 0
    for key in set(dict1.keys())|set(dict2.keys()):
        if key not in dict1.keys():
            cnt_uni += dict2[key]
        elif key not in dict2.keys():
            cnt_uni += dict1[key]
        else:
            cnt_int += min(dict1[key], dict2[key])
            cnt_uni += max(dict1[key], dict2[key])
    
    if not cnt_int and not cnt_uni:
        answer = 1
    else:
        answer = cnt_int / cnt_uni
    return int(answer * 65536)