'use strict';

import React from 'react';
import Comment from './Comment';

class CommentList extends React.Component {
    render() {

        let commentsNodes = this.props.data.map(comment => {
            return (
                <Comment author={comment.author} date={comment.date}>
                    {comment.text}
                </Comment>
            );
        });
        return (
            <div>
                {commentsNodes}
            </div>
        );
    }
}

export {CommentList as default};

