// 生成迭代器

var foods = ['🍅', '🍚'];

function* chef() {
    for (var i = 0; i < foods.length; i++) {
        yield foods[i];
    }
}

let lisi = chef();
console.log(lisi.next());
console.log(lisi.next());
console.log(lisi.next());