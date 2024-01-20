# 读取文件
# open函数用来打开文件，操作文件之前必须打开文件，参数为文件路径 并返回一个文件对象
# as 关键字给返回的对象设置别名 file_object
# with 关键字在文件不需要访问时 会自动关闭文件，类似java close， 相当于调用close方法
with open('assets/pi_digits.txt') as file_object:
    contents = file_object.read()
    print(contents)

# 上述代码与下面代码相同
pi_file = open('assets/pi_digits.txt')
print(pi_file.read())
pi_file.close()

# 也可以使用绝对路径 比如： /home/test/a.txt 或者 C:/Users/test/test.txt
# windows下使用 反斜杠 \ 来替代斜杠，好像不替换也没事

# 逐行读取
with open('assets/pi_digits.txt') as file_object:
    for line in file_object:
        print(line.rstrip())
    # lines = file_object.readlines()
    # for line in lines:
    #     print(line.rstrip())

# 文件写入, 写入同样也要先打开文件, 并且需要写权限， 会自动创建文件
# 二次写入会覆盖
with open('assets/program.txt', 'w') as file_object:
    file_object.write("I love Programing\n")
    file_object.write("I love creating new games\n")

# 附加模式打开文件
# 二次写入不会被覆盖，会自动创建文件
with open('assets/program.txt', 'a') as file_object:
    file_object.write("I love Programing\n")
    file_object.write("I love creating new games\n")


# 如果读取一个不存在的文件，会抛出FileNotFoundError
try:
    with open("assets/sssssssss.txt") as file_object:
        contents = file_object.read()
        print(contents)
except FileNotFoundError:
    print("对不起，文件不存在")
