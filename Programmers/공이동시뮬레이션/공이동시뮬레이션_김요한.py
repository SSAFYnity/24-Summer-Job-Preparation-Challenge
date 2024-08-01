def solution(n, m, x, y, queries):

    # 거꾸로 추적을 할 것이기 때문에 델타를 반대로
    delta = [(0, 1), (0, -1), (1, 0), (-1, 0)]

    positions = [(x, y)]

    while queries:

        query = queries.pop()
        i, j = query[0], query[1]
        mr, mc = delta[i][0] * j, delta[i][1] * j
        save = set()

        for position in positions:

            cr, cc = position

            # 모두 경계선에 없음
            a = 0 < cr < n - 1 and 0 < cc < m - 1
            # row 경계선에 있음 + col 움직임
            b = (cr == 0 or cr == n - 1) and mc != 0
            # col 경계선에 있음 + row 움직임
            c = (cc == 0 or cc == m - 1) and mr != 0
            # 둘 다 경계선에 있음
            d = (cr == 0 or cr == n - 1) and (cc == 0 or cc == m - 1)

            if (a or b or c) and not d:
                nr = cr + mr
                nc = cc + mc
                if nr < 0:
                    nr = 0
                elif nr > n - 1:
                    nr = n - 1
                if nc < 0:
                    nc = 0
                elif nc > m - 1:
                    nc = m - 1
                save.add((nr, nc))

            else:
                save.add((cr, cc))
                for k in (1, j + 1):
                    mr, mc = delta[i][0] * k, delta[i][1] * k
                    nr = cr + mr
                    nc = cc + mc
                    if nr < 0:
                        nr = 0
                    elif nr > n - 1:
                        nr = n - 1
                    if nc < 0:
                        nc = 0
                    elif nc > m - 1:
                        nc = m - 1
                    save.add((nr, nc))

        positions = list(save)

    return len(positions)


