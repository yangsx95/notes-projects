# 字典是键值对
aliens = {'color': 'green', 'points': 5}
print(aliens)

# 访问字典
print(aliens['color'])
# 添加键值对
aliens['x_point'] = 100
aliens['y_point'] = 200
print(aliens)
# 删除键值对
del aliens['points']
print(aliens)

# 遍历字典
for k, v in aliens.items():
    print("遍历key=" + k + " value=" + str(v))
# 获取所有键以遍历，或者用于判断是否存在key
print(aliens.keys())
if 'zhangsan' not in aliens.keys():
    print('张三是好人')
# 获取所有值以遍历
print(aliens.values())
# 提出value重复值
print(set(aliens.values()))

# 嵌套字典
# 字典列表
alien_0 = {'color': 'green', 'points': 5}
alien_1 = {'color': 'yellow', 'points': 10}
alien_2 = {'color': 'red', 'points': 15}
aliens = [alien_0, alien_1, alien_2]
for alien in aliens:
    print(alien)

# 在字典中存储列表
pizza = {
    'crust': 'thick',
    'toppings': ['mushrooms', 'extra cheese']
}

# 字典中存储字典
users = {
    'aeinstein': {
        'first': 'albert',
        'last': 'einstein',
        'location': 'princeton',
    },
    'mcurie': {
        'first': 'marie',
        'last': 'curie',
        'location': 'paris',
    },
}
