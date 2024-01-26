// 获取函数的名称
function test1() {
}

console.log(test1.name); // test1

let test2 = function () {
}
console.log(test2.name); // test2

let test3 = function test4() {
}
console.log(test3.name); // test4


// Arraw Function 箭头函数

// 单个参数，直接返回参数的函数
let demo1 = param1 =>
param1;
/*
function demo1(param1) {
    return param1;
}
*/

// 多个参数，返回简单进行参数处理的函数
let demo2 = (param1, param2) =>
param1 + param2;
/*
function demo2(param1, param2) {
    return param1 + param2;
}
*/

// 多个参数，返回复杂的参数处理函数
let demo3 = (param1, param2) =>
{
    return param1 + param2;
}
/*
function demo2(param1, param2) {
    return param1 + param2;
}
*/

