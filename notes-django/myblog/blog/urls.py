from django.contrib import admin
from django.urls import path

import blog.views as bv

urlpatterns = [
    path('', bv.index),  # 使用http://localhost:8000/blog/访问
    path('index/', bv.index),  # 使用http://localhost:8000/blog/index访问
    path('index.htm', bv.index_htm),
]
