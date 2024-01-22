import pygame
import sys
from bullet import Bullet
from alien import Alien


# 事件管理函数
def check_events(setting, screen, ship, bullets):
    """响应所有键鼠事件"""
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_RIGHT:
                ship.moving_right = True
            elif event.key == pygame.K_LEFT:
                ship.moving_left = True
            elif event.key == pygame.K_UP:
                ship.moving_up = True
            elif event.key == pygame.K_DOWN:
                ship.moving_down = True
            elif event.key == pygame.K_SPACE:
                # 创建一个子弹，并添加到group中
                new_bullet = Bullet(setting, screen, ship)
                bullets.add(new_bullet)
        elif event.type == pygame.KEYUP:
            if event.key == pygame.K_RIGHT:
                ship.moving_right = False
            elif event.key == pygame.K_LEFT:
                ship.moving_left = False
            elif event.key == pygame.K_UP:
                ship.moving_up = False
            elif event.key == pygame.K_DOWN:
                ship.moving_down = False


# 屏幕刷新函数
def update_screen(ai_settings, screen, ship, bullets, aliens):
    """更新屏幕上的图像，并切换到新屏幕"""
    # 每次循环重绘屏幕
    screen.fill(ai_settings.bg_color)
    # 绘制飞船
    ship.blitme()
    # 绘制子弹group
    for bullet in bullets.sprites():
        bullet.draw_bullet()
    # 绘制敌人
    aliens.draw(screen)
    # 让最近的绘制刷新到屏幕
    pygame.display.flip()


def update_bullets(bullets):
    # 更新子弹
    bullets.update()
    # 删除已经移出屏幕的子弹
    for bullet in bullets.copy():
        if bullet.rect.bottom < 0:
            bullets.remove(bullet)


def create_aliens(setting, screen, aliens):
    """创建外星人群"""
    alien = Alien(screen, setting)
    alien_width = alien.rect.width
    available_space_x = setting.screen_width - 2 * alien_width
    number_aliens_x = int(available_space_x / (2 * alien_width))

