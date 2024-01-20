cars = ['audi', 'bmw', 'subaru', 'toyota']

for car in cars:
    if car == 'bmw':
        print(car.upper())
    else:
        print(car.title())

# 不区分大小写比较
# "BWM".lower() == 'bwm'
# 比较不相等
# "BWM".lower() != 'bwm'

# 使用and检查多个条件
if 'a' == 'a' and 'b' != 'c':
    print("and")
# 使用or简单多个条件
if 'a' == 'b' or 'b' != 'c':
    print("or")

# 检查特定值是否包含在列表中
banned_users = ['andrew', 'carolina', 'david']
user = 'marie'
if user not in banned_users:
    print(user.title() + ", you can post a response if you wish.")

# 检查列表是否为空
test_list = []
if test_list:
    print("列表不为空")
else:
    print("列表为空")

# if elif else
age = 12
if age < 4:
    print("小屁孩")
elif age < 18:
    print("还是小屁孩")
else:
    print("。。。")
