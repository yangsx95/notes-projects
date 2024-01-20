# 导入json模块
import json

# 存储json
numbers = [2, 4, 5, 3, 19, 3]
obj = {
    'name': '张三',
    'age': 13
}

# dump 数组
number_filename = 'assets/numbers.json'
with open(number_filename, 'w') as f_obj:
    json.dump(numbers, f_obj)

# dump 对象
obj_filename = 'assets/obj.json'
with open(obj_filename, 'w') as f_obj:
    json.dump(obj, f_obj)

# 读取json数组文件
with open(number_filename) as f_obj:
    contents = json.load(f_obj)
    print(contents)


# 读取json对象文件
with open(obj_filename) as f_obj:
    contents = json.load(f_obj)
    print(contents)

