import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import TFGList from './TFGList';
import TFGEdit from "./TFGEdit";
import ContactoList from './ContactoList';
import ContactoEdit from "./ContactoEdit";

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/usuarios' exact={true} component={TFGList}/>
            <Route path='/usuarios/:id' component={TFGEdit}/>
            <Route path='/contactos'exact={true} component={ContactoList}/>
            <Route path='/contactos/:id' component={ContactoEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;