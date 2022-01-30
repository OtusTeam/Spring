import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';


class Edit extends React.Component {

    emptyItem = {
        name: '',
        transaction: ''
    };
 
    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const word = (fetch('/words/{this.props.match.params.id}')).json();
            this.setState({item: word});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        fetch('/api/words' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/api/words');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Word' : 'Add Word'}</h2>;

        return <div>

            <container>
                {title}
                <form onSubmit={this.handleSubmit}>
                    <formGroup>
                        <label for="name">Word</label>
                        <input type="text" name="name" id="name" value={item.name || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </formGroup>
                    <formGroup>
                        <label for="translation">Translation</label>
                        <input type="text" name="email" id="translation" value={item.translation || ''}
                               onChange={this.handleChange} autoComplete="translation"/>
                    </formGroup>
                    <formGroup>
                        <button color="primary" type="submit">Save</button>{' '}
                        <button color="secondary" tag={Link} to="/api/words">Cancel</button>
                    </formGroup>
                </form>
            </container>
        </div>
    }
}
export default withRouter(Edit);