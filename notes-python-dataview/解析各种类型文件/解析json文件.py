try:
    # python2.x版本
    from urllib2 import urlopen
except ImportError:
    # python3.x版本
    from urllib.request import urlopen

import json
import requests

# 解析豆瓣电影
jsonurl = 'https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&page_limit=50&page_start=0'
response = urlopen(jsonurl)
# 将响应写入到文件中
data = response.read()

with open('download/douban-movie.json', 'wb') as f:
    f.write(data)

# 加载json
load = json.loads(data)
print(load)


# 函数urlopen的方法较为复杂，可以依赖requests包，这个包对urlopen进行了封装，提供了很多常用方法
resp = requests.get(jsonurl)
print(resp.json())
print(resp.text)

doubanMovie = resp.json()
print("---- subject: " + str(doubanMovie['subjects']))
