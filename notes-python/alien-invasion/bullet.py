import pygame
from pygame.sprite import Sprite


# 子弹类继承Sprite类
class Bullet(Sprite):

    def __init__(self, setting, screen, ship):
        super(Bullet, self).__init__()
        self.setting = setting
        self.screen = screen
        self.ship = ship

        # 创建子弹矩形
        self.rect = pygame.Rect(0, 0, setting.bullet_width, setting.bullet_height)
        # 初始位置位于飞船中部
        self.rect.centerx = ship.rect.centerx
        self.rect.top = ship.rect.top
        # 记录子弹位置
        self.y = float(self.rect.y)
        # 背景色
        self.color = setting.bullet_color
        self.speed_factor = setting.bullet_speed_factor

    def update(self):
        """向上移动子弹"""
        self.y -= self.speed_factor
        # 更新子弹位置
        self.rect.y = self.y

    def draw_bullet(self):
        """绘制子弹"""
        pygame.draw.rect(self.screen, self.color, self.rect)

