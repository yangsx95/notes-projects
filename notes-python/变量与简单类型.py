# coding=utf-8

print("hello")
# 2.7 版本，括号可以省略
# print "hello"

# 定义变量
message = "Hello World"
print(message)
# 变量值修改
message = "Hello Python"
print(message)

# 如果语句有异常，导致程序无法运行，python解释器将会提供一个traceback， 他是一条记录，用于指出问题出现的位置以及可能的原因
# print(messages)


# 字符串， 字符串分为两种，单引号包裹与双引号包裹
str1 = "this is a string"
str2 = 'this is also a string'
str3 = 'i am is a "string"'
str4 = "i am also a 'string'"
# 调用字符串方法
# title方法会将每个单词首字母变为大写
print(str1.title())
# 合并拼接字符串
# 使用+拼接字符串
first_name = "无忌"
last_name = "张"
full_name = first_name + last_name
print(full_name)

# 打印空白字符：空格、制表符、换行符等
print("\tPython")
print('\tPython')
# 删除末尾空白
print('空白删除python           '.rstrip())
# 删除首部空白
print('           空白删除python'.lstrip())
# 删除两侧空白
print('       空白删除python     '.strip())

# 数字, 可以直接在控制台运算
print(1 + 2)
print(1 - 2)
print(1 * 2)
# python2 会返回1  而 python3 则会返回1.5， python2要求运算的数字必须有一个是浮点数 比如 3.0/2 ，类似java的向上转型？
print(3 / 2)
# 2的4次方
print(2 ** 4)
# 浮点运算精度问题
print(0.2 + 0.1)
# 求模运算
print(10 % 3)

# 字符串与数值转换
money = 10
# 报错： TypeError: must be str, not int， 整数不可进行字符串拼接，需要将其变为string类型
# print("张三买了" + money + "元" )
print("张三买了" + str(money) + "元")
