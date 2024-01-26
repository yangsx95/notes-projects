'use strict';

import React, {Component} from 'react';

class TV extends Component {
    render() {
        return (
            <div>
                <div>电视节目列表</div>
                {this.props.children}
            </div>
        );
    }
}

export {TV as default};
