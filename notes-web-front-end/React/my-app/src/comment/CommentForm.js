'use strict';

import React from 'react';

class CommentForm extends React.Component {

    handleSubmit(event){
        event.preventDefault(); // 丢弃默认事件
        console.log("用户点击了提交表单按钮，准备提交 ...");
        let author = this.refs.author.value, // author 代表input-author元素，value代表值
            text = this.refs.text.value; // text代表input-text元素
        console.log("评论内容为", author, text);
        // 将评论交给服务端，并显示
        // 需要先将评论信息交给父组件 CommentBox
        this.props.onCommentSubmit({author, text, date: '刚刚'});
    }

    render() {
        return (
            /*使用on事件名称，来触发某个事件*/
            <form className="ui reply form" onSubmit={this.handleSubmit.bind(this)}>
                <div className="field">
                    <input type="text" placeholder="姓名" ref="author"/>
                </div>
                <div className="field">
                    <textarea placeholder="评论" ref="text"/>
                </div>
                <button type="submit" className="ui blue button">
                    添加评论
                </button>
            </form>
        );
    }
}

export {CommentForm as default};

