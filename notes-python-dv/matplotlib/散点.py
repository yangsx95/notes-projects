import matplotlib.pyplot as plt

# 设置绘制三点的坐标与样式，当前坐标x=2 y=4 s代表点的大小
# plt.scatter(2, 4, s=400)

# 绘制多个散点
x_values = [1, 2, 3, 4, 5]
y_values = [1, 4, 9, 16, 25]
# edgecolors 点轮廓设置，密集点可能会连接在一起
plt.scatter(x_values, y_values, s=100, c='red', edgecolors='none')

plt.tick_params(axis='both', which='major', labelsize=14)

plt.show()
