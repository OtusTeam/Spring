//import './App.css';
import React from "react";
import WordComponent from "./Word";

// const Header = (props) => (
//     <h1>{props.title}</h1>
// )
//
// export default class App extends React.Component {
//   constructor() {
//     super();
//     this.state = {words: []};
//   }
//
//   componentDidMount() {
//     fetch('/api/words')
//         .then(response => response.json())
//         .then(words => this.setState({words}))
//   }

  function App() {
    return (
        <div className="App">
          <WordComponent/>
        </div>
    );

}



export default App;

