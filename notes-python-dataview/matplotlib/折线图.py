import matplotlib.pyplot as plt

input_values = [1, 2, 3, 4, 5]
squares = [1, 4, 9, 16, 25]
# 设置折线线条粗细5
plt.plot(input_values, squares, linewidth=5)
# 设置图表标题
plt.title("测试折线图")
# x轴label提示，以及样式
plt.xlabel("Value", fontsize=14)
# y轴label提示，以及样式
plt.ylabel("Square of Value", fontsize=14)
plt.show()
