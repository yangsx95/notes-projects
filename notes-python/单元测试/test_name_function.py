import unittest
from name_function import get_formatted_name


# 继承 TestCase
class NameTestCase(unittest.TestCase):
    """测试name_function.py"""

    def setUp(self) -> None:
        """前置"""
        print("我是前置")
        self.xxx = "123"

    def test_first_last_name(self):
        """是否可以正确输出  san zhang 这种名字"""
        print(self.xxx)
        name = get_formatted_name("san", "zhang")
        self.assertEqual(name, "San Zhang")

# 运行测试用例
# unittest.main()
