# 异常抓取
try:
    print(5/0)
except ZeroDivisionError:
    print("不能除以0")

# else 代码块，在try代码成功执行的时候执行
try:
    answer = 5/1
except ZeroDivisionError:
    print("不能除以0")
else:
    print(answer)

# 异常不处理，跳过
try:
    answer = 5/1
except ZeroDivisionError:
    pass


