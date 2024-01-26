'use strict';

import React from 'react';

class Comment extends React.Component {
    render() {
        return (
            <div className="comment">
                <div className="content">
                    <span className="author">
                        发送人：{this.props.author}
                    </span>
                    <br/>
                    <span className="date">
                        时间：{this.props.date}
                    </span>
                    <div className="text">{this.props.children}</div>
                </div>
            </div>
        );
    }
}

export {Comment as default};