# 列表
hobbies = ['game', 'music', 'study']
print(hobbies)
# 访问列表元素
print(hobbies[0].title())
# 访问最后一个元素
print(hobbies[-1])
# 对列表进行增删改
# 在尾部增加
hobbies.append('test')
print(hobbies)
# 在指定位置增加，在第一个位置添加元素
hobbies.insert(0, 'first')
print(hobbies)
# 修改
hobbies[2] = 'homework'
print(hobbies)
# 使用del 命令删除元素
del hobbies[0]
print(hobbies)
# 使用pop删除指定位置的元素，返回值是被删除元素
print(hobbies.pop(0) + "被删除，结果：" + hobbies.__str__())
# 删除指定的值，使用remove
hobbies.remove('study')
print("study 被删除, 结果：" + hobbies.__str__())

# 列表排序
# 永久性排序，改list对象
sortList = ['a', 'd', 'l', 'g', 'e', 'm', 'z', 'b']
sortList.sort()
print(sortList)
# 临时性排序，输出一个新的list
sortList = ['a', 'd', 'l', 'g', 'e', 'm', 'z', 'b']
print(sorted(sortList))

# 遍历列表
magicians = ['alice', 'david', 'carolina']
for magician in magicians:
    print(magician)

# 使用range创建数值列表， 创建 1~9 的数字列表
print(list(range(1, 10)))
# 支持复数
print(list(range(-1, 10)))
# 支持设置步长
print(list(range(0, 10, 2)))
# 不支持小数
# print(list(range(0, 5, 0.5)))
# 直接使用对range遍历
for i in range(1, 3):
    print(i)

# 对列表数字进行运算
digits = list(range(1, 11))
# 最小值
print(min(digits))
# 最大值
print(max(digits))
# 计算总和
print(sum(digits))

#  列表解析， 由1-10的2次方的数组成的list
squares = [value ** 2 for value in range(1, 11)]
print(squares)
# 生成1-20的数组，并打印
print([value for value in range(1, 21)])
# 通过 给 函数 range() 指定 第三 个 参数 来 创建 一个 列表， 其中 包含 1~ 20 的 奇数； 再 使用 一个 for 循环 将 这些 数字 都 打印 出来。
print([value for value in range(1, 21, 2)])
# 将同一个数字乘三次称为立方。例如，在 Python 中，2 的立方用 2**3 表示。请创建一个列表，其中包含前 10个整数（即 1~10）的立方，再使用一个 for 循 环将这些立方数都打印出来。
print([value ** 3 for value in range(1, 11)])

# 切片——处理list的部分元素
players = ['charles', 'martina', 'michael', 'florence', 'eli']
# 获取前三个球员
print(players[0: 3])
print(players[: 3])
# 获取第二个到第四个球员
print(players[1: 4])
# 获取第三个到最后一个球员
print(players[2:])
# 获取最后三个球员
print(players[-3:])

# 复制列表
my_foods = ['pizza', 'falafel', 'carrot cake']
# 完全复制， 切片与原列表是两个完全不想管的列表
friend_foods = my_foods[:]
friend_foods.append('chicken')
print("My favorite foods are:" + my_foods.__str__())
print("My friend' s favorite foods are:" + friend_foods.__str__())

# 元组 tuple ，不可表的列表，使用圆括号定义
dimensions = (200, 300, 500, 100)
print(dimensions[0])
# dimensions[0] = 100  # TypeError: 'tuple' object does not support item assignment


