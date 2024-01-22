import sys
import pygame
from pygame.sprite import Group
from settings import Setting
from ship import Ship
from alien import Alien
import game_functions as gf


def run_game():
    # 初始化背景设置
    pygame.init()
    # 在游戏中，外星人、飞船等每个元素都是一个surface
    # 设置屏幕大小，传入一个元组，大小为 1200像素x800像素
    # 返回的screen对象是一个 surface，表示整个游戏窗口
    setting = Setting()
    screen = pygame.display.set_mode((setting.screen_width, setting.screen_height))
    # 设置窗口标题
    pygame.display.set_caption("Alien Invasion")
    # 创建主角飞船
    ship = Ship(screen, setting)
    # 创建用于存储子弹的编组
    bullets = Group()
    # 创建敌人外星人编组
    aliens = Group()
    gf.create_aliens(setting, screen, aliens)
    # 游戏使用while循环一直运行
    # 每次while循环都会绘制一个空屏幕，并擦除旧屏幕，使得新屏幕可见
    while True:
        # 监视键盘鼠标事件
        gf.check_events(setting, screen, ship, bullets)
        ship.update()
        gf.update_bullets(bullets)
        # print(len(bullets))
        # 绘制各种元素
        gf.update_screen(setting, screen, ship, bullets, aliens)


run_game()
