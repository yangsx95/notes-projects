// 在ES6之前，JS只有全局作用域和函数作用域
{
    var a = 'a变量';
}
console.log(a); // 可以正常输出

// ES6加入了块级作用域，如果想声明一个只在块作用域中有效的变量，需要使用let关键字
{
    let b = "变量b";
}
//console.log(b); // ReferenceError: b is not defined，let定义的变量的作用域位于块级作用域中

// 同样，在ES6之前，也没有常量（恒量）
const c = '变量c';
//c = '变量cc';
//console.log(c); // TypeError: Assignment to constant variable.

//const c = '新的变量c'; // SyntaxError: Identifier 'c' has already been declared  不能重复声明同一个值

const d = [];
d.push("路飞"); // 正确
d.push("索隆"); // 正确
d.push("山治"); // 正确
// d = []; // 不正确