from collections import defaultdict


def solution(friends, gifts):
    friends.sort()

    point = {}
    give_and_take = {}
    for friend in friends:
        point[friend] = 0
        give_and_take[friend] = defaultdict(int)

    while gifts:
        giver, taker = gifts.pop().split()
        point[giver] += 1
        point[taker] -= 1
        give_and_take[giver][taker] += 1

    upcoming_gifts = defaultdict(int)

    while friends:
        giver = friends.pop()
        for taker in friends:
            candidate = ''
            if give_and_take[giver][taker] > give_and_take[taker][giver]:
                candidate = giver
            elif give_and_take[giver][taker] < give_and_take[taker][giver]:
                candidate = taker
            elif point[giver] > point[taker]:
                candidate = giver
            elif point[giver] < point[taker]:
                candidate = taker
            if candidate:
                upcoming_gifts[candidate] += 1

    upcoming_gifts = sorted(upcoming_gifts.items(), key=lambda item: item[1])

    return upcoming_gifts[-1][1] if upcoming_gifts else 0

