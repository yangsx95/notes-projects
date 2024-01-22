"""
游戏设置模块
"""


class Setting:
    def __init__(self):
        """初始化游戏设置"""
        # 屏幕相关设置
        self.screen_width = 1000
        self.screen_height = 600
        self.bg_color = (230, 230, 230)
        # 飞船移动速度
        self.ship_speed_factor = 1
        # 子弹相关设置
        self.bullet_speed_factor = 1
        self.bullet_width = 3
        self.bullet_height = 15
        self.bullet_color = 60, 60, 60
