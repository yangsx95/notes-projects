from django.shortcuts import render
from django.http import HttpResponse


def index(request):
    return HttpResponse('Hello Django')


def index_htm(request):
    return render(request, 'index.html', {
        "name": "张三"
    })
