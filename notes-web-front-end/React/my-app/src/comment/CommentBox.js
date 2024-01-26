'use strict';

import React from 'react';
import CommentList from './CommentList';
import CommentForm from './CommentForm';
import $ from 'jquery';

class CommentBox extends React.Component {

    constructor(props) {
        super(props);
        this.state = {data: []} // state不同于props，state属于组件本身，如果state中任意值发生变化，则组件将会重新渲染
        this.getComments();
        // setInterval(() => this.getComments(), 5*1000); // 五秒执行一次，热更新
    }

    getComments() { // 使用jquery ajax 加载数据
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            cache: false,
            success: comments => {
                this.setState({data: comments});
            },
            error: (xhr, status, error) => {
                console.log(error);
            }
        });
    };

    handleCommentsSubmit(comment) {
        console.log(comment);
        // 向服务端发送数据
        let comments = this.state.data,
            newComments = comments.concat(comment);
        this.setState({data: newComments})
    }

    render() {
        return (
            <div className='ui comments'>
                <h1>评论</h1>
                <div className="ui divider"/>
                <CommentList data={this.state.data}/>
                <CommentForm onCommentSubmit={this.handleCommentsSubmit.bind(this)}/>
            </div>
        );
    }
}

export {CommentBox as default};