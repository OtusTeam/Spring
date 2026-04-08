import React from 'react'

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
);

export default class App extends React.Component {

    constructor() {
        super();
        this.state = {persons: []};
    }

    componentDidMount() {
        fetch('/api/persons')
            .then(response => response.json())
            .then(persons => this.setState({persons}));
    }

    render() {
        return (
            <React.Fragment>
                <Header title={'Persons'}/>
                <table style={styles.personsTable}>
                    <thead>
                    <tr style={styles.personsTableItem}>
                        <th style={styles.personsTableItem}>ID</th>
                        <th style={styles.personsTableItem}>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.persons.map((person, i) => (
                            <tr style={styles.personsTableItem} key={i}>
                                <td style={styles.personsTableItem}>{person.id}</td>
                                <td style={styles.personsTableItem}>{person.name}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </React.Fragment>
        )
    }
};
