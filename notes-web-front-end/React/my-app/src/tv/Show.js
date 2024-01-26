import React from 'react';

class Show extends React.Component {
    render() {
        return (
            <div>
                <div>
                    电台
                </div>
                {this.props.children}
            </div>
        );
    }
}

export {Show as default};