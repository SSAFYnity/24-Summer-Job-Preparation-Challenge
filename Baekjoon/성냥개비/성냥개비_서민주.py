tc = int(input())
mini = [""]*101
mini[2] = "1"
mini[3] = "7"
mini[4] = "4"
mini[5] = "5"
mini[6] = "6"
mini[7] = "8"
mini[10] = "22"
mini[11] = "20"
mini[17] = "200"
for j in range(8, 101):
    if j==10 or j==11 or j==17:
        continue
    if j%7==0:
        mini[j] = "8" * (j//7)
    elif j%7==1:
        mini[j] = "10" + "8"*(j//7-2)
    elif j%7==2:
        mini[j] = "1" + "8"*(j//7-1)
    elif j%7==3:
        mini[j] = "200" + "8"*(j//7-2)
    elif j%7==4:
        mini[j] = "20" + "8"*(j//7-1)
    elif j%7==5:
        mini[j] = "2" + "8"*(j//7-1)
    elif j%7==6:
        mini[j] = "6" + "8"*(j//7-1)
for _ in range(tc):
    n = int(input())
    if n==2:
        print("1 1")
    elif n==3:
        print("7 7")
    else:
        # 최댓값 구하기
        maxi = ""
        if n%2==0:
            maxi = "1"*int(n//2)
        else:
            maxi = "7" + "1"*int(n//2-1)

        print(mini[n], maxi)