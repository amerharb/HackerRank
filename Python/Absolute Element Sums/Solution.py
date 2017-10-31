def getNegativeCount(offSet):
    if (offSet > 1999):
        return 0
    elif (offSet < -2001):
        return rs[4000]
    else:
        return rs[1999 - offSet]


def getTotalSum(offSet):
    if (offSet < -2000):
        return 0
    elif (offSet > 2000):
        return ts[4000]
    else:
        return ts[offSet + 2000]


n = int(input())
a = list(map(int, input().split()))
q = int(input())
x = list(map(int, input().split()))

r = [0] * 4001

for i in a:
    r[i] += 1

rs = [0] * 4001
t = [0] * 4001
ts = [0] * 4001

rs[0] = r[0]
t[0] = -2000 * r[0]
ts[0] = t[0]
for i in range(1, 4000):
    rs[i] = r[i] + rs[i - 1]
    t[i] = (i - 2000) * r[i]
    ts[i] = t[i] + ts[i - 1]

a = None

totalPN = ts[4000] - (2 * ts[1999])
totalX = 0

for i in x:
    totalX += i
    tot = totalPN + (totalX * (rs[4000] - rs[1999] - getNegativeCount(totalX)))
    tot += (ts[1999] - getTotalSum(-totalX - 1)) * 2
    tot += totalX * (rs[1999] - getNegativeCount(totalX))
    print(tot)
