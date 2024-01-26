import React from 'react';
import ReactDOM from 'react-dom';
// 坑，小于4.0的版本, import {Router, Route, browserHistory, Link, IndexRoute} from 'react-router';
import {BrowserRouter as Router, Route} from 'react-router-dom';

import './index.css';

import App from "./App";
import CommentBox from './comment/CommentBox';
import TV from './tv/TV';
import Show from './tv/Show';
import * as serviceWorker from './serviceWorker';

/*使用Router和Route定义路由，使用Link引用路由*/
/*
ReactDOM.render((
    <Router>
        <Route path="/" component={App}>
            <Route path="/tv" component={TV}>
                <Route path="shows/:id" component={Show}/>
            </Route>
        </Route>
    </Router>
), document.getElementById('root'));
*/

/*启动js文件，使用ReactDOM.render 函数， root 代表的是index.html中id为root的元素*/
//通过prop传递数据
/*
const comments = [
    {"author": "小明", "date": "5分钟前", "text": "天气不错啊！"},
    {"author": "小红", "date": "5分钟前", "text": "出去约啊！"},
];
*/
// ReactDOM.render(<CommentBox data={comments} />, document.getElementById('root'));
//模拟从服务端获取数据
ReactDOM.render(<CommentBox url="comments.json" />, document.getElementById('root'));

serviceWorker.unregister();