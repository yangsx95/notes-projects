from django.db import models


# 定义实体Model
class Article(models.Model):
    title = models.CharField(max_length=32, default='title')
    content = models.TextField(null=True)
