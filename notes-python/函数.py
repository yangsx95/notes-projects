def greet_uer(username="我是默认姓名", age=-1):
    """显示简单的问候语，我是一个文档注释"""
    print("hello " + username)
    if age >= 0:
        print("年龄：" + str(age))
    return "success"


greet_uer()
greet_uer("张三", 100)
greet_uer("李四")


# 列表、字典等，是引用传递

def test_list(names):
    for name in names:
        print(name)
    names.append("哈哈")


names = ["张三", "李四"]
test_list(names)
print(names)

# 不允许修改列表可以传入副本
test_list(names[:])
print(names)


# 传递任意数量实参
def print_colors(name, *color):
    print("name=" + name)
    for c in color:
        print(c)


print_colors("红", "黄", "蓝")
