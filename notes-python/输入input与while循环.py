# 使用while对list元素验证并转移
users = [
    {
        'username': '张三',
        'age': 30
    },
    {
        'username': '李四',
        'age': 18
    },
    {
        'username': '王五',
        'age': 13
    },
]
yes_user = []
while users:
    current_user = users.pop()
    if current_user['age'] >= 18:
        yes_user.append(current_user)
print(yes_user)

# 获取字符串
name = input("请输入姓名：")
# 获取数字
age = int(input("请输入年龄："))

# python2.7使用 raw_input获取输入
print("你的姓名为" + name + ", 年龄为" + str(age))

info = ""
while info.strip() != 'q':
    info = input("请随便输入(输入q可退出循环)：")

# 使用退出标志
active = True
while active:
    message = input("使用标志进行循环，输入q退出：")
    if message == 'q':
        active = False
    else:
        print("msg：" + message)
# while循环支持break 与 continue


# 死循环
while True:
    print(100)
    break
