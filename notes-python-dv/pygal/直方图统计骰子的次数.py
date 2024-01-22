import pygal
from random import randint


class Die:
    """骰子类"""

    def __init__(self, num_side=6):
        self.num_side = num_side

    def roll(self):
        """返回一个随机骰子值"""
        return randint(1, self.num_side)


die = Die()
result = []
for roll_num in range(100):
    n = die.roll()
    result.append(n)

print(result)

# 分析每种情况的次数
frequencies = []
for v in range(7):
    frequencies.append(result.count(v))

print(frequencies)

# 绘制直方图

hist = pygal.Bar()
hist.title = "Results of rolling one D6 1000 times."
hist.x_labels = ['1', '2', '3', '4', '5', '6']
hist.x_title = "Result"
hist.y_title = "Frequency of Result"
# 添加到分组D6，每个分组拥有不同的颜色
hist.add('D6', frequencies)
hist.render_to_file('die_visual.svg')
