def solution(n, m, x, y, queries):
    top, bottom, left, right = x, x, y, y

    for command, move in reversed(queries):
        if command == 0:
            if left != 0:
                left += move
            right = min(m - 1, right + move)
        elif command == 1:
            if right != m - 1:
                right -= move
            left = max(0, left - move)
        elif command == 2:
            if top != 0:
                top += move
            bottom = min(n - 1, bottom + move)
        elif command == 3:
            if bottom != n - 1:
                bottom -= move
            top = max(0, top - move)
        
        if left > right or top > bottom:
            return 0
    
    return (right - left + 1) * (bottom - top + 1)