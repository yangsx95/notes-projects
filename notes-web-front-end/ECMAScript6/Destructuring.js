// 数组的解构
function breakfast() {
    return ["🥛", "🍞", "🥓"];
}

// ES5 将breakfast中的元素一一赋值给一个变量
var temp = breakfast();
var milk = temp[0];
var bread = temp[1];
var bacon = temp[2];
console.log(milk, bread, bacon);

// ES6可以使用
let [milkN, breadN, baconN] = breakfast();
console.log(milk, bread, bacon);


// 对象的解构
function sports() {
    return {football: '⚽', basketball: '🏀', pingPong: '🏓'};
}

// 将sports.football 赋值给变量footballN ...
let {football: footballN, basketball: basketballN, pingPong: pingPongN} = sports();
console.log(footballN, basketballN, pingPongN);


// 函数的解构参数
// 给与解构参数默认值，否则不传会报错
function study(name, time, {location, classes} = {}) {
    console.log(name, time, location, classes); // 解构参数，可以直接获取对象中的值
}

study('李四', '明天'); // 李四 明天 undefined undefined
study('张三', '后天', {location: 'home', classes: 'Math'}); // 张三 后天 home Math