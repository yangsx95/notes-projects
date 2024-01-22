import matplotlib.pyplot as plt

x_values = list(range(1, 1001))
y_values = [x ** 2 for x in x_values]

plt.plot(x_values, y_values, linewidth=5)
plt.title("平方数走势")
plt.xlabel("Value", fontsize=14)
plt.ylabel("Square of Value", fontsize=14)

# c设置为一个y值列表，并告诉pyplot使用蓝色作为颜色映射，此时y值较小的情况颜色就比较淡
cm = plt.cm.get_cmap('Blues')
sc = plt.scatter(x_values, y_values, c=y_values, cmap=cm, edgecolor='none', s=40)
# 颜色条
plt.colorbar(sc)

# 设置坐标轴的取值范围
# plt.axis([0, 1100, 0, 1100])
plt.axis([0, 1100, 0, 1100000])

plt.show()
# 保存图片
# plt.savefig('平方走势.png', bbox_inches='tight')
