// JS也可以定义类了
class Chef {
    constructor(food) {
        this.food = food; // 声明属性
        this.dish = [];
    }

    cook() {
        console.log(this.food);
    }

    // getter and setter
    get menu() {
        return this.dish;
    }

    set menu(dish) {
        this.dish.push(dish);
    }

    // static method 不需要创建对象就可以调用
    static cook(food) {
        console.log('我炒了' + food)
    }
}

let lisi = new Chef('🍅');
lisi.menu = '🍕';
lisi.menu = '🍞';
lisi.cook();
console.log(lisi.menu);

// 调用静态方法
Chef.cook('🍅');


// 类也可以继承
class Person {
    constructor(name, birthday) {
        // 定义属性
        this.name = name;
        this.birthday = birthday;
    }

    info() {
        return `姓名：${this.name}, 出生日期: ${this.birthday}`;
    }
}

// Person的子类
class Student extends Person {
    constructor(name, birthday) {
        super(name, birthday);
    }

    // 重写方法
    info() {
        return super.info() + " 是一个学生";
    }
}

let zhangsan = new Student('张三', '1995年9月2日');
console.log(zhangsan.info())