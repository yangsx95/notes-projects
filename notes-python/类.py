# 2.7 创建对象 class Dog(object)
class Dog:
    """模拟小狗类"""

    def __init__(self, name, age):
        self.name = name
        self.age = age

    def sit(self):
        """蹲下"""
        print(self.name.title() + "蹲下了")

    def roll_over(self):
        """模拟小狗打滚"""
        print(self.name.title() + " 打滚了")


# __init__方法，创建实例时都会调用此方法，是python的默认方法，有点像构造函数
# 两个下划线开始的，是python的默认方法

# 创建实例
my_dog = Dog("旺财", 3)
print("我的小狗名叫" + my_dog.name + "， 今年" + str(my_dog.age) + "了")
my_dog.roll_over()


class Car:
    """汽车类"""

    def __init__(self, make, model, year):
        """初始化汽车属性"""
        self.make = make
        self.model = model
        self.year = year
        """里程碑，默认值0"""
        self.odometer_reading = 0

    def get_descriptive_name(self):
        """返回汽车信息"""
        long_name = str(self.year) + ' ' + self.make + ' ' + self.model
        return long_name.title()

    def read_odometer(self):
        """读取里程碑数"""
        return self.odometer_reading

    def add_odometer(self, step):
        """添加里程碑"""
        self.odometer_reading += step


# 创建一辆新车
my_new_car = Car('audi', 'a4', 2016)
print("汽车信息：" + my_new_car.get_descriptive_name())
# 直接修改里程碑属性
my_new_car.odometer_reading = 23
# 打印里程碑
print(my_new_car.read_odometer())
# 通过方法修改里程碑属性
my_new_car.add_odometer(10)
print(my_new_car.read_odometer())


# 使用继承, 定义电动车
class ElectricCar(Car):
    """我是电动车"""

    def __init__(self, make, model, year):
        """初始化父类属性"""
        super().__init__(make, model, year)
        """电动车的特有属性:电瓶容量"""
        self.battery_size = 70

    def describe_battery(self):
        """打印电瓶容量"""
        print("电动车" + self.model + "的电瓶容量为" + str(self.battery_size))

    def get_descriptive_name(self):
        """重写父类方法"""
        long_name = str(self.year) + '~' + self.make + '~' + self.model
        return long_name.title()


my_tesla = ElectricCar('tesla', 'model s', 2016)
print(my_tesla.get_descriptive_name())
my_tesla.describe_battery()


# 导入类， 一个模块可以存储多个类，正如存储多个函数， 用逗号分开
# from car import Car
# 导入所有模块的类
# from car import *
