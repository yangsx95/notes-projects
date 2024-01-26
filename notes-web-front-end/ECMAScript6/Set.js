// 无序集合 Set
let food = new Set('🍎🍌🍅🥓🥔');
console.log(food.size); // 5
// 添加元素
food.add('🍉');
console.log(food, food.size); // 不相同元素会添加
food.add('🍎');
console.log(food, food.size); // 相同元素不会添加 6

// 删除元素
food.delete('🍉');
console.log(food, food.size); // 删除西瓜，5

// 判断元素是否存在于集合中
console.log(food.has('🍉')); // false

// 遍历元素
food.forEach(f = > {
    console.log(f);
})

// 清空set
food.clear();
console.log(food, food.size); // 0