import React from "react";

const Header = (props) => (
    <h1>{props.title}</h1>
)

export default class WordComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            words: []
        }
    }
    handleDelete() {
        this.props.onDelete(this.props.employee);
    }

    componentDidMount() {
        fetch('/api/words')
            .then(response => response.json())
            .then(words => this.setState({words}))
    }

    render() {
        return (
            <div>
                <Header className = "text-center" title={'Words'}/>
                <table>
                    <thead>
                    <tr>
                        <th>Word ID</th>
                        <th>Word</th>
                        <th>Translation</th>
                        <th>Context</th>
                        <th>Example</th>
                        <th>Added date</th>
                        <th>Learnt date</th>
                        <th>State</th>
                        <th>Learnt percent</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.words.map((word, i) => (
                            <tr key={i}>
                                <td>{word.id}</td>
                                <td>{word.name}</td>
                                <td>{word.translate}</td>
                                <td>{word.context}</td>
                                <td>{word.example}</td>
                                <td>{word.addedDate}</td>
                                <td>{word.learntDate}</td>
                                <td>{word.state}</td>
                                <td>{word.learntPercent}</td>
                                <td>
                                    <button onClick={this.handleDelete}>Delete</button>
                                </td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        )


    }

}