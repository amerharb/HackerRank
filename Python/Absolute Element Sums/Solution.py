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
