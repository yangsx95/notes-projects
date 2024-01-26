// 模板字符串
// ES6提供了模板字符串

let dessert = '🍞'
drink = '🍺';
let breakfastMsg = `今天的早餐是${dessert},
    并且喝个${drink}`; // 可以换行
console.log(breakfastMsg);


// Tagged Template 带标签的模板
let money = 20;
let breakfastMsg2 = kitchen`今天的早餐是${dessert},并且喝个${drink}, 总共花费了${money}`;

// 这里kitchen函数是用于生成模板字符串的函数
// strings+values就是目标字符串，values是插值
// 通常用于字符串预处理，比如传入了一个html标签，去除标签样式，让其变为纯文本
function kitchen(strings,

...
values
)
{
    // console.log(strings); // [ '今天的早餐是', ',\n并且喝个', '' ]
    // console.log(values);  //[ '🍞', '🍺' ]
    let result = '';
    result += strings[0];
    result += values[0];
    result += strings[1];
    result += "一杯" + values[1];
    result += strings[2];
    result += values[2] + '元';
    return result;
}

console.log(breakfastMsg2);
