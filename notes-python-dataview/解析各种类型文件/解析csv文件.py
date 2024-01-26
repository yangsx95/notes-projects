import csv
import codecs
from datetime import datetime
from matplotlib import pyplot as plt

filename = 'death_valley_2014.csv'

with codecs.open(filename, 'r', 'utf-8') as f:
    reader = csv.reader(f)
    header_row = next(reader)
    print(header_row)

    # enumerate函数会将目标list转换为一个拥有属性index 和 element的对象
    for index, column_header in enumerate(header_row):
        print(str(index) + ", " + column_header)

    # 遍历天气数据, reader对象会从上次读取的位置继续读取
    # 读取第0列即日期值存放到dates中, 读取第1列即Max TemperatureF最高温度，存放到highs中
    # 读取第3列表放入lows中作为最低气温
    dates, highs, lows = [], [], []

    for row in reader:
        try:
            current_date = datetime.strptime(row[0], '%Y-%m-%d')
            high = int(row[1])  # 将其转换为数字类型
            low = int(row[3])
        except ValueError:
            print(current_date, "miss data")
        else:
            dates.append(current_date)
            highs.append(high)
            lows.append(low)

figure = plt.figure(dpi=128, figsize=(10, 6))
plt.title("2014年气温概览", fontsize=24)
plt.xlabel('日期', fontsize=16)
figure.autofmt_xdate()  # 使用autofmt_xdate 绘制x轴日期，防止重叠
plt.ylabel('温度', fontsize=16)
plt.tick_params(axis='both', which='major', labelsize=16)

# 绘制最高温图表、最低气温的折线
plt.plot(dates, highs, c='red', alpha=0.5)  # 颜色与透明度
plt.plot(dates, lows, c='blue', alpha=0.5)

# 给两个数据集之间着色
plt.fill_between(dates, highs, lows, facecolor='gray', alpha=0.1)

plt.show()
