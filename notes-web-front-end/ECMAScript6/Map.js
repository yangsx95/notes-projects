// js对象默认就是一个map,为什么还需要map？ 
// 因为js对象的key只能是string类型，不可以是复杂的对象

// 新建Map
let food = new Map();
let fruit = {}, cook = function () {
}, dessert = '甜点';

// 向map中添加元素
food.set(fruit, '🍋');
food.set(cook, '🔪');

// 获取map的长度
console.log(food.size);

// 获取指定元素
console.log(food.get(fruit));

// 删除某个元素
food.delete(fruit);

// 判断元素是否存在
console.log(food.has(fruit));

// 遍历map
console.log("-------");
food.forEach((key, value) = > {
    console.log(key, value);
})
;

// 清空map
food.clear();