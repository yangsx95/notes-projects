# 在自然界、物理学、生物学、化学和经济领域，随机漫步都有其实际用途。
# 例如，漂浮在水滴上的花粉因不断受到水分子的挤压而在水面上移动。
# 水滴中的分子运动是随机的，因此花粉在水面上的运动路径犹如随机漫步。

# 模拟随机漫步，创建RandomWalk类
from random import choice
import matplotlib.pyplot as plt


class RandomWalk:
    def __init__(self, num_points=5000):
        self.num_points = num_points

        # 用来记录点的轨迹
        # 所有漫步都始于 [0, 0]
        self.x_values = [0]
        self.y_values = [0]

    def fill_walk(self):
        # 不断漫步，直到列表达到指定的长度
        while len(self.x_values) < self.num_points:
            # choice 从一个list中随机选择一个值
            x_direction = choice([1, -1])
            x_distance = choice([0, 1, 2, 3, 4])
            x_step = x_direction * x_distance

            y_direction = choice([1, -1])
            y_distance = choice([0, 1, 2, 3, 4])
            y_step = y_direction * y_distance

            # 不允许不移动
            if x_step == 0 and y_step == 0:
                continue

            # 根据step计算下一个点的坐标, 并记录到轨迹数组中
            next_x = x_step + self.x_values[-1]
            next_y = y_step + self.y_values[-1]
            self.x_values.append(next_x)
            self.y_values.append(next_y)


# 创建一个随机漫步对象，漫步5000次
rw = RandomWalk(5000)
rw.fill_walk()

plt.scatter(rw.x_values, rw.y_values, s=15, c=rw.x_values, cmap=plt.cm.get_cmap("YlOrRd"))
# 突出起点与终点
plt.scatter(rw.x_values[0], rw.y_values[0], c='black')
plt.scatter(rw.y_values[-1], rw.y_values[-1], c='green')

# 隐藏x y 坐标轴
plt.axes().get_xaxis().set_visible(False)
plt.axes().get_yaxis().set_visible(False)

plt.show()

