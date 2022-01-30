import React from "react";
import { Link } from 'react-router-dom';

const styles = {
    personsTable: {
        border: "1px solid steelblue",
        width: "300px",
        borderCollapse: "collapse",
    },

    personsTableItem: {
        padding: "5px",
        border: "1px solid steelblue"
    }
}

const Header = (props) => (
    <h1>{props.title}</h1>
)

export default class WordComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {words: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/api/words')
            .then(response => response.json())
            .then(words => this.setState({words}))
    }

    remove(id) {
        fetch('/api/words/{id}', {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedWords = [...this.state.words].filter(i => i.id !== id);
            this.setState({words: updatedWords});
        });
    }

    render() {
        return (
            <div>
                <div className="float-right">
                    <button color="success" tag={Link} to="/api/words/new">Add Word</button>
                </div>
                <Header className="text-center" title={'Words'}/>
                <table style={styles.personsTable}>
                    <thead>
                    <tr style={styles.personsTableItem}>
                        <th style={styles.personsTableItem}>Word ID</th>
                        <th style={styles.personsTableItem}>Word</th>
                        <th style={styles.personsTableItem}>Translation</th>
                        <th style={styles.personsTableItem}>Context</th>
                        <th style={styles.personsTableItem}>Example</th>
                        <th style={styles.personsTableItem}>Added date</th>
                        <th style={styles.personsTableItem}>Learnt date</th>
                        <th style={styles.personsTableItem}>State</th>
                        <th style={styles.personsTableItem}>Learnt percent</th>
                        <th style={styles.personsTableItem}>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.words.map((word, i) => (
                            <tr style={styles.personsTableItem} key={i}>
                                <td style={styles.personsTableItem}>{word.id}</td>
                                <td style={styles.personsTableItem}>{word.name}</td>
                                <td style={styles.personsTableItem}>{word.translation}</td>
                                <td style={styles.personsTableItem}>{word.context}</td>
                                <td style={styles.personsTableItem}>{word.example}</td>
                                <td style={styles.personsTableItem}>{word.addedDate}</td>
                                <td style={styles.personsTableItem}>{word.learntDate}</td>
                                <td style={styles.personsTableItem}>{word.state}</td>
                                <td style={styles.personsTableItem}>{word.learntPercent}</td>
                                <td>
                                    <buttonGroup>
                                        <button size="sm" color="primary" tag={Link} to={"/api/words/" + word.id}>Edit</button>
                                        <button size="sm" color="danger" onClick={() => this.remove(word.id)}>Delete</button>
                                    </buttonGroup>
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