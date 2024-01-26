import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Link} from 'react-router-dom';


/**
 * 定义一个 React Component ，继承 Rect.Component
 * App 是一个React组件类
 * 一个组件可以接收 props的参数，通过render方法，返回一个嵌套结构的视图
 */
class App extends Component {
    // render 函数是对渲染内容的描述，返回一个React元素
    // 比如 <div /> 将会被编译为 React.createElement('div')
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <p>
                        Hello World，这是我的ReactJS示例
                    </p>
                    <a className="App-link"
                       href="https://reactjs.org"
                       target="_blank"
                       rel="noopener noreferrer">
                        Learn React
                    </a>
                </header>
            </div>
        );
    }
}

export default App;
