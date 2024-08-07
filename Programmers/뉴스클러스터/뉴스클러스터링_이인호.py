

def solution(str1, str2):
    arr1 =[]
    arr2 =[]
    for i in range(len(str1)-1):
        k = str1[i:i+2]
        k = k.upper()
        if k.isalpha():
            arr1.append(k)
    for i in range(len(str2)-1):
        k = str2[i:i+2]
        k = k.upper()
        if k.isalpha():
            arr2.append(k)

    inter =[]
    arr1_tmp = arr1.copy()
    sumarr = arr1.copy()

    for i in arr2:
        if i not in arr1_tmp:
            sumarr.append(i)
        else:
            arr1_tmp.remove(i)

    for i in arr1:
        if i in arr2:
            arr2.remove(i)
            inter.append(i)

    if len(sumarr) != 0:
        answer = int((len(inter)/len(sumarr))*65536)
    else:
        answer = 65536
    return answer