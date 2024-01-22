import pygame


class Alien:
    def __init__(self, screen, setting):
        """外星人类（敌人）"""
        super(Alien, self).__init__()
        self.screen = screen
        self.setting = setting
        self.image = pygame.image.load('images/alien.bmp')
        self.rect = self.image.get_rect()
        self.screen_rect = screen.get_rect()
        self.rect.x = self.rect.width
        self.rect.y = self.rect.height
        self.x = float(self.rect.x)

    def blitme(self):
        """在指定位置绘制飞船"""
        self.screen.blit(self.image, self.rect)

