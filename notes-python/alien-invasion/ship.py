import pygame


class Ship:

    def __init__(self, screen, setting):
        """
        初始化飞船并设置初始位置
        :param screen: 屏幕对象
        """
        self.screen = screen
        self.setting = setting

        # 加载图像并获取其外接矩形， 这是一个surface对象
        self.image = pygame.image.load('images/ship.bmp')
        # 获取图像的rect对象，是一个矩形包含一些坐标信息
        self.rect = self.image.get_rect()
        self.screen_rect = screen.get_rect()

        # 将飞船放在屏幕底部中央
        # 设置飞船的水平中心为屏幕中心， 飞船的底部放入到屏幕底部
        self.rect.centerx = self.screen_rect.centerx
        self.rect.bottom = self.screen_rect.bottom

        # 移动标志，用于表示当前移动状态
        self.moving_right = False
        self.moving_left = False
        self.moving_up = False
        self.moving_down = False

    def update(self):
        """根据移动标志调整飞船位置"""
        if self.moving_right and self.rect.right < self.screen_rect.right:
            self.rect.centerx += self.setting.ship_speed_factor
        elif self.moving_left and self.rect.left > self.screen_rect.left:
            self.rect.centerx -= self.setting.ship_speed_factor
        elif self.moving_up and self.rect.top > self.screen_rect.top:
            self.rect.centery -= self.setting.ship_speed_factor
        elif self.moving_down and self.rect.bottom < self.screen_rect.bottom:
            self.rect.centery += self.setting.ship_speed_factor

    def blitme(self):
        """在指定位置绘制飞船"""
        self.screen.blit(self.image, self.rect)

# pygame 的原点位于屏幕左上角
