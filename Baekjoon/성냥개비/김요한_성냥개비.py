rule = {
    2: ["1"],
    3: ["7"],
    4: ["4"],
    5: ["2", "3", "5"],
    6: ["0", "6", "9"],
    7: ["8"]
}

for _ in range(int(input())):

    match = input()

    # minimum
    min_val, min_len = "9999999999", 0
    tmp_lst = list()
    num1, num2 = int(match), 7
    while True:
        if num1 <= 1:
            num1 += tmp_lst.pop()
            num2 -= 1
        if num1 in rule:
            tmp_lst.append(num1)
            break
        else:
            tmp_lst.append(num2)
        num1 -= num2
    tmp_set = set()
    tmp_set.add(tuple(sorted(tmp_lst)))
    if len(tmp_lst) == 1:
        min_val = rule[tmp_lst[0]][0]
        if min_val == "0":
            min_val = "6"
    else:
        i = 1
        while i < len(tmp_lst):
            tmp_lst[-i] += 1
            tmp_lst[-i - 1] -= 1
            key = tuple(sorted(tmp_lst))
            if key in tmp_set or key[-1] >= 8:
                i += 1
            else:
                tmp_set.add(key)
        combs = list(tmp_set)
        for comb in combs:
            val = sorted([rule[_][0] for _ in comb])
            if all("0" == _ for _ in val):
                val[0] = "6"
            else:
                i = 1
                while val[0] == "0":
                    if val[i] > "6":
                        val[0] = "6"
                    else:
                        val[0], val[i] = val[i], val[0]
                        i += 1
            val = "".join(val)
            min_val = min(min_val, val)

    # maximum
    max_val, max_len = "0", 0
    for num2 in range(2, 8):
        tmp = []
        num1 = int(match)
        while True:
            if num1 - num2 > 1:
                tmp.append(num2)
                num1 -= num2
            elif num1 in rule:
                tmp.append(num1)
                break
            else:
                num2 -= 1
        if max_len == 0:
            max_len = len(tmp)
        elif max_len > len(tmp):
            break
        tmp = sorted([rule[_][-1] for _ in tmp],
                     reverse=True)
        val = "".join(tmp)
        max_val = max(max_val, val)

    print(min_val, max_val)
