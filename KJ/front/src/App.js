import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  const [state, setState] = useState([]);

  const URL = "https://jsonplaceholder.typicode.com/posts";

useEffect (
  () => {
    axios.get(URL).then((res) => {
      setState(res.data);
    });
    state && console.log(state);
  }, []);



  return (
    <div>
     
      김경준
      {state.map((v) => (
        <div>
        <div style={
          {padding: "10px", color: "blue"}}
        >{v.title}</div>
        <div style={
          {padding: "10px", color: "blue"}}
        >{v.id}</div>
        </div>
      )
      )}
    </div>
  );
}

export default App;
